package ru.renue.fts.asktt.client.camel.api;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Component;
import ru.renue.fts.asktt.client.data.entities.MsgInformation;
import ru.renue.fts.asktt.client.data.persistence.MsgInformationRepository;


/**
 * Created by disap on 27.07.2017.
 */
@Component
public class RouteManager {
    @Autowired
    private CamelContext camelContext;
    @Autowired
    private ProducerTemplate producerTemplate;
    @Autowired
    private MsgInformationRepository msgInformationRepository;

    /**
     * Добавление очереди для прослушивания.
     * @param queueName Имя очереди.
     * @param componentName имя jms компонента.
     * @return Успешность операции добавления.
     */
    public boolean addRoute(final String queueName, final String componentName){
        if (isQueueListening(queueName, componentName)){
            //todo: залогирвоать, такая очередь уже прослушивается, вместо boolean можно возвращать статус операции (enum)
            return true;
        }else{
            try {
                camelContext.addRoutes(new RouteBuilder() {
                    public void configure() {
                        from(componentName+":queue:"+queueName)
                                .routeId(queueName)
                                .log(LoggingLevel.INFO, "Send to "+queueName+"\n").bean(MyCamelConsumer.class, "receiveMessage(Exchange,${body})");//todo: перенаправить считывание очереди в метод
                    }
                });
                //todo: залогировать успешное добавление очереди(если нужно), вроде camel логирует сам
            }catch (Exception ex){
                //todo: залогировать причину по которой очередь не может добавиться, принять какие-то меры
                return false;
            }
        }
        return true;
    }

    /**
     * Остановка прослушивания очереди по id.
     * id совпадает с названием очереди.
     * @param routeId id маршрута
     * @return
     */
    public boolean stopRouteById(final String routeId){
        try{
            Route route = camelContext.getRoute(routeId);
            if (route != null){
                camelContext.stopRoute(routeId);
                camelContext.removeRoute(routeId);
            }
            return true;
        }catch (Exception ex){
            //todo: залогировать почему данная очередь не может быть остановлена или удалена
            return false;
        }
    }

    /**
     * Проверка на прослушивание очереди.
     * @param queuename
     * @param componentName
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
     * отправка сообщения в Mq и сохранение в базу.
     * Статус сохранения - SENT
     * @param msgInformation данные для сохранения в базу (hib)
     * @param queueName Имя очереди
     * @param componentName Имя компонента
     * @return
     */
    public boolean sendAndSave(final MsgInformation msgInformation,final String queueName,final String componentName){
        try{
            msgInformationRepository.save(msgInformation);
            producerTemplate.sendBody(componentName+":queue:"+queueName, msgInformation.getData());
        }catch(Exception ex){
            //todo: Запись в лог о неуспешном добавлении в базу либо отправки сообщения в очередь
            //todo: разбить исключения по типам
            return false;
        }
        return true;
    }
}
