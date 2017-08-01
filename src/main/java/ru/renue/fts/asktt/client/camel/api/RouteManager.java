package ru.renue.fts.asktt.client.camel.api;

import ch.qos.logback.classic.Logger;
import com.ibm.msg.client.jms.DetailedInvalidDestinationException;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.hibernate.exception.JDBCConnectionException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.renue.fts.asktt.client.data.entities.MsgInformation;
import ru.renue.fts.asktt.client.data.persistence.MsgInformationRepository;

import javax.jms.JMSException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;


/**
 * Created by disap on 27.07.2017.
 */
@Component
public class RouteManager {
    //время жизни очереди в милисекундах, если клиент её не опрашивает
    private static long timeOfAging = 3600 * 1000;

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private MsgInformationRepository msgInformationRepository;



    private Logger logger = (Logger) LoggerFactory.getLogger(RouteManager.class);

    /**
     * Время последнего опроса очередей.
     */
    private HashMap<String, Date> lastPollingTime = new HashMap<>();

    /**
     * Добавление очереди для прослушивания.
     * @param queueName Имя очереди.
     * @param componentName имя jms компонента.
     * @return Успешность операции добавления.
     */
    public boolean addRoute(final String queueName, final String componentName){
        if (isQueueListening(queueName, componentName)){
            logger.info("Очередь " + componentName + ":" + queueName + " прослушивается.");
            return true;
        }else{
            try {
                camelContext.addRoutes(new RouteBuilder() {
                    public void configure() {
                        from(componentName+":queue:"+queueName)
                                .routeId(queueName).transacted()
                                .bean("myCamelConsumer", "receiveMessage(Exchange,${body})");//todo: перенаправить считывание очереди в метод
                    }
                });
                lastPollingTime.put(queueName, new Date());
                logger.info("Стартовало прослушивание очереди: " + componentName + ":" + queueName + ".");
            }catch (Exception ex){
                logger.error("Невозможно подключиться к очереди: " + componentName + ":" + queueName + ".");
                return false;
            }
        }
        return true;
    }

    /**
     * Удаление прослушивания очереди по id.
     * id совпадает с названием очереди.
     * @param routeId id маршрута
     * @return
     */
    public boolean removeRouteById(final String routeId){
        Route route = camelContext.getRoute(routeId);
        try{
            if (route != null && stopRouteById(routeId)){
                camelContext.removeRoute(routeId);
                lastPollingTime.remove(routeId);
                return true;
            }

        }catch (Exception ex){
            // TODO: 28.07.2017 вывести название очереди
            logger.error("Очередь"+route+" не может быть удалена ");
            return false;
        }
        return false;
    }

    /**
     * Остановка очереди по id.
     * Не путать с удалением очереди.
     * @param routeId id очереди.
     * @return true/false
     */
    private boolean stopRouteById(final String routeId){
        Route route = camelContext.getRoute(routeId);
        try{
            if (route!=null)
                camelContext.stopRoute(routeId);
            return true;
        }
        catch (Exception ex){
            // TODO: 28.07.2017  Вывести название очереди
            logger.error("Очередь " + camelContext +"не может быть остановлена. " + ex.getMessage());
            return false;
        }

    }

    /**
     * Старт остановленной очереди.
     * Не путать с добавлением очереди.
     * Остановленная очередь имеется в списке путей.
     *
     * @param routeId id очереди
     * @return true/false
     */
    private boolean startRouteById(final String routeId){
        Route route = camelContext.getRoute(routeId);
        try{
            if (route != null){
                camelContext.startRoute(routeId);
            }
        }
        catch (Exception ex){
            // TODO: 28.07.2017  вывести полное имя очереди которая не может быть запущена
            logger.error("Остановленная очередь" + routeId + " не может быть запущена. " + ex.getMessage());
            return false;
        }
        // TODO: 28.07.2017 вывести полное имя запущенной очереди
        logger.info("Очередь " + routeId + " успешно запущена.");
        return true;
    }

    /**
     * Проверка на прослушивание очереди.
     * @param queuename Имя очереди.
     * @param componentName Название компонента.
     * @return
     */
    private boolean isQueueListening(final String queuename, final String componentName) {
        Endpoint endpoint = camelContext.hasEndpoint(componentName+":"+ queuename);
        if (endpoint == null) {
            endpoint = camelContext.hasEndpoint(componentName + ":queue:" + queuename);
            if (endpoint==null)
                return false;
        }
        return true;
    }

    /**
     *
     */
    public boolean removeIfOutDated(final String routeId){
        if (new Date().getTime() - lastPollingTime.get(routeId).getTime() > timeOfAging) {
            if (removeRouteById(routeId))
                return true;
        }
        return false;
    }
    /**
     * Обновление времени обращения клиента к сервису.
     * @param routeId
     * @return
     */
    public void updateLastPollingTime(final String routeId){
        lastPollingTime.put(routeId, new Date());
    }
    /**
     * отправка сообщения в Mq и сохранение в базу.
     * Статус сохранения - SENT
     * @param msgInformation данные для сохранения в базу (hib)
     * @param queueName Имя очереди
     * @param componentName Имя компонента
     * @return
     */
    @Transactional
    public boolean sendAndSave(final MsgInformation msgInformation,final String queueName,final String componentName){
        String destinationName = componentName+":queue:"+queueName;
        try{
            msgInformationRepository.save(msgInformation);
            producerTemplate.sendBody(destinationName, msgInformation.getData());
        }
        catch (CamelExecutionException camelException){
            logger.error("Отправка в сообщения в очередь " + destinationName + " не удалась: " + camelException.getMessage());
            return false;
        }
        catch(JDBCConnectionException jdbcException){
            logger.error("Соединение с базой не было установлено: " + jdbcException.getMessage());
            return false;
        }
        catch (Exception ex){
            logger.error("Неизвестная ошибока: " + ex.getMessage());
        }
        logger.info("Запись о сообщении добавлена в базу с id = " + msgInformation.getId()
                + "; Сообщение отправлено в очередь " + destinationName
                + ". Размер сообщения: " + msgInformation.getData().length + " байт.");
        return true;
    }


}
