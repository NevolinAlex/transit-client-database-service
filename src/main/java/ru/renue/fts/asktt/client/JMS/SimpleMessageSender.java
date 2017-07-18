package ru.renue.fts.asktt.client.JMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by disap on 18.07.2017.
 */
interface MessageSender {
    void send(String message);
}

@Component
public class SimpleMessageSender implements MessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(final String message) {
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }
}