package ru.renue.fts.asktt.client.config;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import ru.renue.fts.asktt.client.JMS.SimpleMessageListener;

import javax.jms.JMSException;

/**
 * Created by disap on 18.07.2017.
 */
@ComponentScan({"ru.renue.fts.asktt.client.JMS"})
@Configuration
public class JMSConfiguration {
    @Value("${mq.host}")
    private String host;
    @Value("${mq.port}")
    private Integer port;
    @Value("${mq.queue-manager}")
    private String queueManager;
    @Value("${mq.channel}")
    private String channelname;
    @Value("${mq.queue-receiver-name}")
    private String receiverName;
    @Value("${mq.queue-destination-name}")
    private String destinationName;

    @Autowired
    private SimpleMessageListener messageListenerContainer;

    @Bean
    public MQQueueConnectionFactory connectionFactory() {

        MQQueueConnectionFactory connectionFactory = new MQQueueConnectionFactory();
        try {
            connectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
            connectionFactory.setHostName(host);
            connectionFactory.setPort(port);
            connectionFactory.setQueueManager(queueManager);
            connectionFactory.setChannel(channelname);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return connectionFactory;
    }

    @Bean
    public DefaultMessageListenerContainer listenerContainer(){
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setDestinationName(receiverName);
        container.setMessageListener(messageListenerContainer);
        return container;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setDefaultDestinationName(destinationName);
        return jmsTemplate;
    }

}
