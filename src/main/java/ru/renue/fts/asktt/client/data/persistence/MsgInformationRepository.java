package ru.renue.fts.asktt.client.data.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.renue.fts.asktt.client.data.enums.DocumentStatus;
import ru.renue.fts.asktt.client.data.entities.MsgInformation;
import java.util.ArrayList;

/**
 * Created by disap on 21.07.2017.
 */
@Transactional
public interface MsgInformationRepository extends CrudRepository<MsgInformation, Long> {

    /**
     * Удаление документа по id таможни.
     * @param customQueue
     */
    int deleteByCustomQueue(String customQueue);
    /**
     * Поиск записей из базы, по ID таможни и статусу документа.
     * @param customQueue очередь с которой читает таможня.
     * @param documentStatus Статус документа принятого/отправленного в очередь.
     * @return Возвращает список записей подходящих по условию.
     */
    ArrayList<MsgInformation> findByCustomQueueAndDocumentStatus(String customQueue, DocumentStatus documentStatus);

    /**
     * Получает первое непрочитанное сообщение из базы.
     * Первое по времени (самое старое)
     * @param customQueue Имя очереди.
     * @param documentStatus
     * @return Либо сообщение, либо null, если все прочитаны
     */
    MsgInformation findFirstByCustomQueueAndDocumentStatus(String customQueue, DocumentStatus documentStatus);
}
