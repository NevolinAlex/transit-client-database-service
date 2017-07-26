//package ru.renue.fts.asktt.client.config;
//
//import com.ibm.mq.jms.MQQueueConnectionFactory;
//import com.ibm.msg.client.wmq.WMQConstants;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.annotation.EnableJms;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.listener.DefaultMessageListenerContainer;
//import org.springframework.jms.listener.MessageListenerContainer;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import ru.renue.fts.asktt.client.jms.MQMessageListener;
//
//import javax.jms.JMSException;
//
//@ComponentScan({"ru.renue.fts.asktt.client.jms"})
//@EnableJms
//@EnableTransactionManagement
//@Configuration
//public class JmsConfig {
//
//    @Value("${wmq.channelName}")
//    private String channelName;
//
//    @Value("${wmq.queueManager}")
//    private String queueManager;
//
//    @Value("${wmq.queueDestinationName}")
//    private String queueDestinationName;
//
//    @Value("${wmq.queueRecievedName}")
//    private String queueRecieverName;
//
//    @Value("${wmq.port}")
//    private int port;
//
//    @Value("${wmq.hostname}")
//    private String hostName;
//
//
//    @Autowired
//    private MQMessageListener messageListener;
//
//    /**
//     * Create connection factory.
//     *
//     * @return connectionFactory
//     */
//    @Bean
//    public MQQueueConnectionFactory connectionFactory() {
//
//        MQQueueConnectionFactory connectionFactory = new MQQueueConnectionFactory();
//        try {
//            connectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
//            connectionFactory.setHostName(hostName);
//            connectionFactory.setPort(port);
//            connectionFactory.setQueueManager(queueManager);
//            connectionFactory.setChannel(channelName);
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//        return connectionFactory;
//    }
//
//    /**
//     * Create Template.
//     *
//     * @return
//     */
//    @Bean
//    public JmsTemplate jmsTemplate() {
//        JmsTemplate template = new JmsTemplate();
//        template.setConnectionFactory(connectionFactory());
//        template.setDefaultDestinationName(queueDestinationName);
//        return template;
//    }
//
//    /*@Autowired
//    private TestStepConfig testStepConfig;*/
//
//    /**
//     * Create MessageListener.
//     *
//     * @return
//     */
//    @Bean
//    public MessageListenerContainer jmsListenerContainerFactory() {
//        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory());
//        container.setDestinationName(queueRecieverName);
//        container.setMessageListener(messageListener);
//        return container;
//    }
//}
