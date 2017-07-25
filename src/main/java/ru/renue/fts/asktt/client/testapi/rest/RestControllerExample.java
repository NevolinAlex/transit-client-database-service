package ru.renue.fts.asktt.client.testapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.renue.fts.asktt.client.entities.DocumentDao;
import ru.renue.fts.asktt.client.entities.MessageInfo;
import ru.renue.fts.asktt.client.entities.MessageInfoRepository;
import ru.renue.fts.asktt.client.enums.DocumentStatus;
import java.util.Date;
import java.util.List;
/**
 * Created by disap on 18.07.2017.
 */
@RestController
public class RestControllerExample {
    private static final byte[] TEST_ARRAY= new byte[]{11,12};
    private static final long TEST_CUSTOM_ID = 1050060;

    @Autowired
    private DocumentDao documentDao;

    @Autowired
    private MessageInfoRepository messageInfoRepository;

    /**
     * Метод для тестирования функционала hibernate.
     * создание нового объекта, сохарнение его с помощью JPA
     * @return Записи подходязщие условию
     */
    @RequestMapping("/test")
    public String test() {
        MessageInfo messageInfo = new MessageInfo(TEST_ARRAY, DocumentStatus.SENT, TEST_CUSTOM_ID, new Date());
        try {
            messageInfoRepository.save(messageInfo);
            List<MessageInfo> messageInfoList = messageInfoRepository.
                    findByCustomIdAndDocumentStatus(messageInfo.getCustomId(), messageInfo.getDocumentStatus());
            messageInfoRepository.delete(messageInfo.getId());
            return messageInfoList.toString();
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
    }

    /**
     * Удаление по id таможни.
     * @param customId Id таможни
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam("customId") final long customId){
        int numberOfnotes;
        try {
            numberOfnotes = messageInfoRepository.deleteByCustomId(customId);
        }
        catch (Exception ex){
            return "Error deleting the user: " + ex.toString();
        }
        return "Users deleted succesfuly! " + numberOfnotes + " - notes.";
    }

}
