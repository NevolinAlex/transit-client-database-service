package ru.renue.fts.asktt.client.hessian.implementation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.renue.fts.asktt.client.data.persistence.MsgInformationRepository;

import static org.junit.Assert.*;

/**
 * Created by disap on 27.07.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageCheckerTest {
    @Autowired
    private MsgInformationRepository msgInformationRepository;
    @Autowired
    private MessageChecker messageChecker;
    @Test
    public void checkByMessage() throws Exception {
        byte[] actual = messageChecker.checkByMessage("10502060.INCOME");
        assertEquals(new byte[]{1,2,3}, actual);
    }

}