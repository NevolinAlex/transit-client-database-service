package ru.renue.fts.asktt.client.hessian.implementation;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.renue.fts.asktt.client.camel.api.RouteManager;
import ru.renue.fts.asktt.client.data.entities.MsgInformation;
import ru.renue.fts.asktt.client.data.enums.DocumentStatus;
import ru.renue.fts.asktt.client.data.persistence.MsgInformationRepository;
import ru.renue.fts.asktt.client.hessian.api.IMqSender;

import java.util.Date;

/**
 * Created by disap on 26.07.2017.
 */
@Component
public class MqSenderImpl implements IMqSender {
    private static String DESTINATION_QUEUE = "10502060.INCOME";
    private static String COMPONENT_NAME = "wmq";
    @Autowired
    private RouteManager routeManager;

    @Override
    public void sendMessage(final String queueName, final byte[] array) {
        MsgInformation msgInformation = new MsgInformation(array, DocumentStatus.SENT, queueName, new Date());
        try{
            if (routeManager.addRoute(queueName, COMPONENT_NAME)){
               routeManager.sendAndSave(msgInformation, queueName, COMPONENT_NAME);
            }
        }
        catch (Exception ex){
            //todo: залогировать, и возможно отправить респонс клиенту а том что с сервисом/очепредью/базой что-то не то
        }

    }
}
