package ru.renue.fts.asktt.client.jms;

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
//
interface MessageSender {
    /**
     * Интерфейс для реализации передачи сообщений.
     * @param message
     */
    void send(String message);
}

/**
 * Класс занимающийся отправкой сообщений в очередь.
 */
@Component
public class SimpleMessageSender implements MessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * Метод отправки солобщения в очередь.
     * @param message
     */
    public void send(final String message) {
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(final Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }
}