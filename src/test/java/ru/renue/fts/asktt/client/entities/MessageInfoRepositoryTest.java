package ru.renue.fts.asktt.client.entities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.renue.fts.asktt.client.enums.DocumentStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by disap on 21.07.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageInfoRepositoryTest {


    private static final long TEST_CUSTOM_ID = 10510600;
    private static final byte[] TEST_BYTE_ARRAY = new byte[]{1,2};

    @Autowired
    private MessageInfoRepository messageInfoRepository;

    /**
     * Тестирует фукнциональность Hibernate.
     * Сохранение, поиск и удаление данных
     * в message_information
     * @throws Exception
     */
    @Test
    public void findByCustomIdAndDocumentStatusTest() throws Exception {
        MessageInfo messageInfo = new MessageInfo(TEST_BYTE_ARRAY, DocumentStatus.SENT, TEST_CUSTOM_ID, new Date());
        messageInfoRepository.save(messageInfo);
        ArrayList<MessageInfo> messageInfoList = messageInfoRepository.
                findByCustomIdAndDocumentStatus(messageInfo.getCustomId(), messageInfo.getDocumentStatus());
        messageInfoRepository.delete(messageInfo.getId());
        assertTrue(messageInfo.equals(messageInfoList.get(messageInfoList.size()-1)));
    }

    /**
     * Тест на удаление по customId.
     * из таблицы message_information
     * @throws Exception
     */
    @Test
    public void deleteByCustomId() throws Exception {
        long customId = 111;
        MessageInfo messageInfo = new MessageInfo(TEST_BYTE_ARRAY, DocumentStatus.SENT, customId, new Date());
        messageInfoRepository.save(messageInfo);
        int deletedCount = messageInfoRepository.deleteByCustomId(customId);
        assertEquals(1, deletedCount);
    }

}