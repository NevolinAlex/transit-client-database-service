package ru.renue.fts.asktt.client.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;


import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * Created by disap on 31.07.2017.
 */
@Configuration
public class JtaConfig {
    /**
     * Пользовательские транзакции jms
     * @return
     * @throws Throwable
     */
    @Bean
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(300);
        return userTransactionImp;
    }

    /**
     *
     * @return
     * @throws Throwable
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    public TransactionManager userTransactionManager() throws Throwable {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        userTransactionManager.setTransactionTimeout(300);
        userTransactionManager.setStartupTransactionService(false);
        return userTransactionManager;
    }

    /**
     * Менеджер транзакций для jms.
     *
     * @return
     * @throws Throwable
     */
    @Bean
    public JtaTransactionManager transactionManager() throws Throwable {
        JtaTransactionManager transactionManager = new JtaTransactionManager(userTransaction(), userTransactionManager());
        transactionManager.setDefaultTimeout(300);
        return transactionManager;
    }
}