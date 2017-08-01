package ru.renue.fts.asktt.client.hessian.implementation;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.renue.fts.asktt.client.camel.api.RouteManager;
import ru.renue.fts.asktt.client.data.entities.MsgInformation;
import ru.renue.fts.asktt.client.data.enums.DocumentStatus;
import ru.renue.fts.asktt.client.data.persistence.MsgInformationRepository;
import ru.renue.fts.asktt.client.hessian.api.IMqSender;

import javax.jms.JMSException;
import java.util.Date;

/**
 * Created by disap on 26.07.2017.
 */
@Component
public class MqSenderImpl implements IMqSender {
    //private static String destinationQueue = "10502060.INCOME";
    private static String componentName = "wmq";

    @Autowired
    private RouteManager routeManager;

    @Override
    @Transactional
    public boolean sendMessage(final String receiveQueue, final byte[] array, final String destinationQueue) {
        MsgInformation msgInformation = new MsgInformation(array, DocumentStatus.SENT, receiveQueue, new Date());
        try{
            if (routeManager.addRoute(receiveQueue, componentName)){
                return routeManager.sendAndSave(msgInformation, destinationQueue, componentName);
            }
        }
        catch (Exception ex){

        }
        return false;
    }
}
