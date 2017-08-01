package ru.renue.fts.asktt.client.logback;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.renue.fts.asktt.client.data.persistence.MsgInformationRepository;

/**
 * Created by disap on 28.07.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogBackTest {

    @Autowired
    private MsgInformationRepository msgInformationRepository;
    /**
     * Тестирование конфига логгера.
     * используется для тестирования особенностей логирования.
     */
    @Test
    public void logBackConfigureTest(){
        Logger logger = (Logger) LoggerFactory.getLogger(LogBackTest.class);
        logger.info("Hello world.");
        Class class1 = msgInformationRepository.getClass();
        logger.info(msgInformationRepository.getClass().toString());



        // print internal state
//        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//        StatusPrinter.print(lc);
    }
}
