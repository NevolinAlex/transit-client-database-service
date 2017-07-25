package ru.renue.fts.asktt.client.jms;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by disap on 25.07.2017.
 */
@Component
public class MQMessageListener implements MessageListener {

    @Override
    public void onMessage(final Message message) {
        //TODO: обработчик входящего сообщения - добавить в базу
        try{
            int a = 10;
        }
        catch (Exception ex){

        }

    }
}
