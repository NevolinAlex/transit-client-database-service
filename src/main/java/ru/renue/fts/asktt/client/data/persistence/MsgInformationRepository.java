package ru.renue.fts.asktt.client.data.persistence;

import org.springframework.data.repository.CrudRepository;
import ru.renue.fts.asktt.client.data.enums.DocumentStatus;
import ru.renue.fts.asktt.client.data.entities.MsgInformation;
import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * Created by disap on 21.07.2017.
 */
@Transactional
public interface MsgInformationRepository extends CrudRepository<MsgInformation, Long> {

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
    ArrayList<MsgInformation> findByCustomIdAndDocumentStatus(long customId, DocumentStatus documentStatus);
}
