package ru.renue.fts.asktt.client.camel.api;

import ch.qos.logback.classic.Logger;
import org.apache.camel.Exchange;
import org.hibernate.exception.JDBCConnectionException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.renue.fts.asktt.client.data.entities.MsgInformation;
import ru.renue.fts.asktt.client.data.enums.DocumentStatus;
import ru.renue.fts.asktt.client.data.persistence.MsgInformationRepository;

import javax.persistence.Table;
import java.util.Date;

/**
 * Created by disap on 27.07.2017.
 */
@Component("myCamelConsumer")
public class MyCamelConsumer {
    // TODO: 28.07.2017 унаследовать от интерфейса; зачем? спроси у Димы
    private Logger logger = (Logger) LoggerFactory.getLogger(MyCamelConsumer.class);

    @Autowired
    private MsgInformationRepository msgInformationRepository;

    @Autowired
    private RouteManager routeManager;
    /**
     * Получение и обработка сообщения из очереди.
     */
    @Transactional
    public void receiveMessage(final Exchange exchange, final byte[] body){

        String routeId = exchange.getFromRouteId();
        if (routeManager.removeIfOutDated(routeId)){
            // TODO: 01.08.2017 послать сообщение обратно в очередь 
            return;
        }

        MsgInformation msgInformation = new MsgInformation(body, DocumentStatus.RESEIVED, routeId, new Date());
        try{
            msgInformationRepository.save(msgInformation);
        }
        catch (JDBCConnectionException exception){
            // TODO: 31.07.2017 Вывести имя таблицы либо схемы базы
            logger.info("Ну удалось установить соединение с базой " + msgInformationRepository.getClass().getAnnotations());
        }
        logger.info("Сообщение размером " + body.length + " получено из очереди " + routeId + ".");
        logger.info("Сообщение успешно записано в базу с id=" + msgInformation.getId() + ".");
    }
}
