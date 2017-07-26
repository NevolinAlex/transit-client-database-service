package ru.renue.fts.asktt.client.hessian.service;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.renue.fts.asktt.client.data.entities.MsgInformation;
import ru.renue.fts.asktt.client.data.enums.DocumentStatus;
import ru.renue.fts.asktt.client.data.persistence.MsgInformationRepository;

import java.util.Date;

/**
 * Created by disap on 26.07.2017.
 */
@Component
public class IMqSenderImpl implements IMqSender{
    private static String DESTINATION_QUEUE = "10502060.INCOME";
    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private MsgInformationRepository msgInformationRepository;

    @Override
    public void sendmessage(final String queueName, final byte[] array) {
        MsgInformation msgInformation = new MsgInformation(array, DocumentStatus.SENT, Long.parseLong(queueName), new Date());
        try{
            msgInformationRepository.save(msgInformation);
            producerTemplate.sendBody(DESTINATION_QUEUE, array);
        }
        catch (Exception ex){

        }

    }
}
