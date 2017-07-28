package ru.renue.fts.asktt.client.logback;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by disap on 28.07.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogBackTest {
    /**
     * Тестирование конфига логгера.
     */
    @Test
    public void logBackConfigureTest(){
        Logger logger = (Logger) LoggerFactory.getLogger(LogBackTest.class);
        logger.info("Hello world.");



        // print internal state
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
    }
}
