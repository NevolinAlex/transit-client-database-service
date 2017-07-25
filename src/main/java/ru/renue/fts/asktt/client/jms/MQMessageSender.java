package ru.renue.fts.asktt.client.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by disap on 25.07.2017.
 */
@Component
public class MQMessageSender {
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * Отправка сообщения на сервер.
     * @param message
     */
    public void sendMessage(final byte[] message){

    }
}
