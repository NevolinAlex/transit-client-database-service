package ru.renue.fts.asktt.client.entities;

import org.springframework.data.repository.CrudRepository;
import ru.renue.fts.asktt.client.enums.DocumentStatus;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by disap on 21.07.2017.
 */
@Transactional
public interface MessageInfoRepository extends CrudRepository<MessageInfo, Long> {

    /**
     * Удаление документа по id таможни.
     * @param customId
     */
    int deleteByCustomId(long customId);
    /**
     * Поиск записей из базы, по ID таможни и статусу документа.
     * @param customId ID таможни
     * @param documentStatus Статус документа принятого/отправленного в очередь
     * @return Возвращает список записей подходящих по условию
     */
    ArrayList<MessageInfo> findByCustomIdAndDocumentStatus(long customId, DocumentStatus documentStatus);
}
