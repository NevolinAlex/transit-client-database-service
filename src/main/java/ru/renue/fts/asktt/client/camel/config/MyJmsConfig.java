package ru.renue.fts.asktt.client.camel.config;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.SingleConnectionFactory;

import javax.jms.JMSException;

/**
 * Created by disap on 26.07.2017.
 */
@Configuration
public class MyJmsConfig {
    @Value("${wmq.channelName}")
    private String channelName;

    @Value("${wmq.queueManager}")
    private String queueManager;

    @Value("${wmq.port}")
    private int port;

    @Value("${wmq.hostname}")
    private String hostName;
    /**
     * Конфигурация JmsComponent.
     * @return
     */
    @Bean
    public JmsComponent jmsComponent(){
        JmsComponent jmsComponent = new JmsComponent();
        jmsComponent.setConcurrentConsumers(1);
        jmsComponent.setMaxConcurrentConsumers(1);
        jmsComponent.setConnectionFactory(singleConnectionFactory());
        return jmsComponent;
    }

    /**
     * Фабрика соединений SingleCF.
     * @return
     */
    public SingleConnectionFactory singleConnectionFactory(){
        SingleConnectionFactory singleConnectionFactory = new SingleConnectionFactory(mqQueueConnectionFactory());
        return singleConnectionFactory;

    }

    /**
     * Фабрика соединений MQ.
     * @return MQ фабрика соединений
     */
    public MQQueueConnectionFactory mqQueueConnectionFactory() {

        MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
        try {
            mqQueueConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
            mqQueueConnectionFactory.setHostName(hostName);
            mqQueueConnectionFactory.setPort(port);
            mqQueueConnectionFactory.setQueueManager(queueManager);
            mqQueueConnectionFactory.setChannel(channelName);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return mqQueueConnectionFactory;
    }

}
