package ru.renue.fts.asktt.client.hessian.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.renue.fts.asktt.client.data.entities.MsgInformation;
import ru.renue.fts.asktt.client.data.enums.DocumentStatus;
import ru.renue.fts.asktt.client.data.persistence.MsgInformationRepository;
import ru.renue.fts.asktt.client.hessian.api.IChecker;

/**
 * Created by disap on 27.07.2017.
 */
@Component
public class MessageChecker implements IChecker {
    @Autowired
    private MsgInformationRepository msgInformationRepository;
    @Override
    public byte[] checkByMessage(final String queueName) {
        MsgInformation msgInformation = msgInformationRepository
                .findFirstByCustomQueueAndDocumentStatus(queueName, DocumentStatus.RESEIVED);
        if (msgInformation != null){
            msgInformation.setDocumentStatus(DocumentStatus.READ);
            msgInformationRepository.save(msgInformation);
            return msgInformation.getData();
        }
        return null;
    }
}
