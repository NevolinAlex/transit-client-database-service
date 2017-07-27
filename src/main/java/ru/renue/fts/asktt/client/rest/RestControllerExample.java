package ru.renue.fts.asktt.client.rest;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.renue.fts.asktt.client.camel.api.MqExecutor;
import ru.renue.fts.asktt.client.data.entities.MsgInformation;
import ru.renue.fts.asktt.client.data.persistence.MsgInformationRepository;
import ru.renue.fts.asktt.client.data.enums.DocumentStatus;
import ru.renue.fts.asktt.client.hessian.implementation.MqSenderImpl;

import java.util.Date;
import java.util.List;
/**
 * Created by disap on 18.07.2017.
 */
@RestController
public class RestControllerExample {
    private static final byte[] TEST_ARRAY= new byte[]{11,12};
    private static final String TEST_QUEUE_NAME = "1050060";
    @Autowired
    private CamelContext camelContext;
    private MqExecutor mqExecutor;
    @Autowired
    private MqSenderImpl mqSender;

    @Autowired
    private MsgInformationRepository msgInformationRepository;

    /**
     * Метод для тестирования функционала hibernate.
     * создание нового объекта, сохарнение его с помощью JPA
     * @return Записи подходязщие условию
     */
    @RequestMapping("/test")
    public String test() {
        MsgInformation messageInfo = new MsgInformation(TEST_ARRAY, DocumentStatus.SENT, TEST_QUEUE_NAME, new Date());
        try {
            msgInformationRepository.save(messageInfo);
            List<MsgInformation> messageInfoList = msgInformationRepository.
                    findByCustomQueueAndDocumentStatus(messageInfo.getCustomQueue(), messageInfo.getDocumentStatus());
            msgInformationRepository.delete(messageInfo.getId());
            return messageInfoList.toString();
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
    }

    /**
     * Удаление по id таможни.
     * @param customQueue Id таможни
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam("customId") final String customQueue) throws Exception {
        int numberOfnotes;
        try {
            numberOfnotes = msgInformationRepository.deleteByCustomQueue(customQueue);
        }
        catch (Exception ex){
            return "Error deleting the user: " + ex.toString();
        }
        mqSender.sendMessage("10502060.INCOME", new byte[]{1,2,3});
        //producerTemplate.sendBody("wmq:queue:10502060.INCOME", "test");
//        camelContext.stopRoute("100");
//        camelContext.removeRoute("100");
        return "Users deleted succesfuly! " + numberOfnotes + " - notes.";
    }

}
