package ru.renue.fts.asktt.client.camel.config;

import org.apache.camel.CamelContext;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by disap on 26.07.2017.
 */
@Configuration
public class MyCamelConfig {
    @Autowired
    private JmsComponent jmsComponent;

    /**
     * настраиваем контекст.
     * @return
     */
    @Bean
    CamelContextConfiguration contextConfiguration() {
        return new CamelContextConfiguration (){
            @Override
            public void beforeApplicationStart(final CamelContext context) {
                context.addComponent("wmq", jmsComponent);
//                try {
//                    context.addRoutes(new RouteBuilder() {
//                        public void configure() {
//                            from("wmq:queue:10502060.INCOME").routeId("100").log(LoggingLevel.INFO, "Success! ${body}").to("file://test");
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void afterApplicationStart(final CamelContext camelContext) {

            }
        };
    }
}

