package ru.renue.fts.asktt.client.camel.api;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.renue.fts.asktt.client.data.entities.MsgInformation;
import ru.renue.fts.asktt.client.data.enums.DocumentStatus;
import ru.renue.fts.asktt.client.data.persistence.MsgInformationRepository;

import java.util.Date;

/**
 * Created by disap on 27.07.2017.
 */
@Component
public class MyCamelConsumer {
    // TODO: 28.07.2017 унаследовать от интерфейса; зачем? спроси у Димы
    @Autowired
    private MsgInformationRepository msgInformationRepository;
    /**
     * Получение и обработка сообщения из очереди.
     */
    public void receiveMessage(final Exchange exchange, final byte[] body){
        String routeID = exchange.getFromRouteId();
        MsgInformation msgInformation = new MsgInformation(body, DocumentStatus.RESEIVED, routeID, new Date());
        msgInformationRepository.save(msgInformation);
        System.out.println("Received from " + routeID);

    }
}
