package ru.renue.fts.asktt.client.config;

import com.atomikos.jms.AtomikosConnectionFactoryBean;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.mq.jms.MQXAQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.jms.JMSException;
import javax.jms.XAConnectionFactory;
import javax.naming.NamingException;
import javax.transaction.TransactionManager;

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

    @Value("${pool.size}")
    private Integer poolSize;

    @Autowired
    private JtaTransactionManager jtaTransactionManager;
    /**
     * Конфигурация JmsComponent.
     * @return
     */
    @Bean
    public JmsComponent jmsComponent() throws JMSException, NamingException {
        JmsComponent jmsComponent = new JmsComponent();
        jmsComponent.setConcurrentConsumers(1);
        jmsComponent.setMaxConcurrentConsumers(1);
        jmsComponent.setTransacted(true);
        jmsComponent.setTransactionManager(jtaTransactionManager);
        jmsComponent.setConnectionFactory(atomikosConnectionFactoryBean());
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

    /**
     * Фабрика соединения бинов.
     * @return
     * @throws NamingException
     * @throws JMSException
     */
    @Bean
    public AtomikosConnectionFactoryBean atomikosConnectionFactoryBean() throws NamingException, JMSException{
        AtomikosConnectionFactoryBean singleConnectionFactory = new AtomikosConnectionFactoryBean();
        singleConnectionFactory.setPoolSize(poolSize);
        singleConnectionFactory.setUniqueResourceName("MYQ");
        singleConnectionFactory.setLocalTransactionMode(false);
        singleConnectionFactory.setXaConnectionFactory(jmsConnectionFactory());
        return singleConnectionFactory;
    }

    /**
     * Фабрика соединений jms
     * @return
     */
    public XAConnectionFactory jmsConnectionFactory() throws JMSException, NamingException{
        MQXAQueueConnectionFactory factory = new MQXAQueueConnectionFactory();
        factory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
        factory.setHostName(hostName);
        factory.setPort(port);
        factory.setQueueManager(queueManager);
        factory.setChannel(channelName);
        return factory;
    }
}
