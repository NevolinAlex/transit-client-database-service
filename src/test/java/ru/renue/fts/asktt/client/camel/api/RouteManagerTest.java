package ru.renue.fts.asktt.client.camel.api;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by disap on 27.07.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteManagerTest {
    @Autowired
    private CamelContext camelContext;
    public static String QUEUE_NAME = "10502060.INCOME";
    @Test
    public void isQueueListening() throws Exception {
        Endpoint endpoint = camelContext.hasEndpoint("wmq:queue:"+QUEUE_NAME);
        assertTrue(true);
    }

}