package ru.renue.fts.asktt.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import ru.renue.fts.asktt.client.HessianTest.CalculationImpl;

/**
 * Created by disap on 19.07.2017.
 */
@ComponentScan({"ru.renue.fts.asktt.client.HessianTest"})
@Configuration
@ImportResource("classpath:/hessian.xml")
public class HessianConfig {

    @Autowired
    private CalculationImpl calculation;
}
