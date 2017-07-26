package ru.renue.fts.asktt.client.camel.api;

import java.util.logging.*;
import org.apache.camel.CamelContext;
import org.apache.camel.LoggingLevel;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by disap on 26.07.2017.
 */
public class MqExecutor {
    @Autowired
    private CamelContext camelContext;
    @Autowired
    private ProducerTemplate producerTemplate;

    /**
     * Отправление запроса в mq.
     * @param queueName имя очереди
     * @param body Объект для отправки
     * @return Успешность операции
     */
    public boolean checkAndSend(final String queueName, final Object body){
        try{
            producerTemplate.sendBody("wmq:queue:" + queueName, body);
            return true;
        }
        catch (Exception ex){
            System.out.println("Message not sent");
            return false;
            //log.log(LoggingLevel.ERROR, "Error to send message in queue "+queueName);
        }
    }
}
