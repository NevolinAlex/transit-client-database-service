package ru.renue.fts.asktt.client.hessian.implementation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.renue.fts.asktt.client.camel.api.RouteManager;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by disap on 27.07.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqSenderImplTest {
    @Autowired
    private MqSenderImpl mqSender;
    @Autowired
    private RouteManager routeManager;
    public static String QUEUE_NAME = "10502060.INCOME";
    @Test
    public void sendMessage() throws Exception {
        routeManager.addRoute("10502060.INCOME", "wmq");
        TimeUnit.SECONDS.sleep(5);
        assertTrue(mqSender.sendMessage(QUEUE_NAME, new byte[]{1,2,4}, QUEUE_NAME));
        TimeUnit.SECONDS.sleep(3);
        TimeUnit.SECONDS.sleep(3);
        routeManager.removeRouteById("10502060.INCOME");
        TimeUnit.SECONDS.sleep(3);
    }

}