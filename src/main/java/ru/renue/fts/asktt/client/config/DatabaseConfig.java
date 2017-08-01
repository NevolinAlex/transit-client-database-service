package ru.renue.fts.asktt.client.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by disap on 31.07.2017.
 */
@Configuration
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassname;

    /**
     * Atomikos источник данных.
     * @return
     */
    @Bean
    public DataSource atomikasDataSource(){
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setUniqueResourceName("oracle");
        atomikosDataSourceBean.setXaDataSourceClassName("oracle.jdbc.xa.client.OracleXADataSource");
        //atomikosDataSourceBean.setTestQuery("select * from mq_data_information;");
       // atomikosDataSourceBean.setBorrowConnectionTimeout(30);
        //atomikosDataSourceBean.setMaxPoolSize(3);
        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        properties.setProperty("URL", url);
        atomikosDataSourceBean.setPoolSize(3);
        atomikosDataSourceBean.setXaProperties(properties);
        return atomikosDataSourceBean;
    }


}
