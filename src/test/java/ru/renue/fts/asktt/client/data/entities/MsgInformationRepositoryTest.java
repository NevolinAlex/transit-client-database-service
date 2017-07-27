package ru.renue.fts.asktt.client.data.entities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.renue.fts.asktt.client.data.enums.DocumentStatus;
import ru.renue.fts.asktt.client.data.persistence.MsgInformationRepository;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by disap on 21.07.2017.
 * Тестирование функциональности Hibernate.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MsgInformationRepositoryTest {

    private static final String TEST_CUSTOM_ID = "10502060.INCOME";
    private static final byte[] TEST_BYTE_ARRAY = new byte[]{1,2};

    @Autowired
    private MsgInformationRepository msgInformationRepository;

    /**
     * Тест на поиск записей.
     * в message_information по customId и docStatus
     * @throws Exception
     */
    @Test
    public void findByCustomIdAndDocumentStatusTest() throws Exception {
        MsgInformation msgInformation = new MsgInformation(TEST_BYTE_ARRAY, DocumentStatus.SENT, TEST_CUSTOM_ID, new Date());
        msgInformationRepository.save(msgInformation);
        ArrayList<MsgInformation> msgInformationList = msgInformationRepository.
                findByCustomQueueAndDocumentStatus(msgInformation.getCustomQueue(), msgInformation.getDocumentStatus());
        msgInformationRepository.delete(msgInformation.getId());
        assertTrue(msgInformation.equals(msgInformationList.get(msgInformationList.size()-1)));
    }

    /**
     * Тест на удаление по customId.
     * из таблицы message_information
     * @throws Exception
     */
    @Test
    public void deleteByCustomId() throws Exception {
        String customQueue = "111";
        MsgInformation msgInformation = new MsgInformation(TEST_BYTE_ARRAY, DocumentStatus.SENT, customQueue, new Date());
        msgInformationRepository.save(msgInformation);
        int deletedCount = msgInformationRepository.deleteByCustomQueue(customQueue);
        assertEquals(1, deletedCount);
    }

}