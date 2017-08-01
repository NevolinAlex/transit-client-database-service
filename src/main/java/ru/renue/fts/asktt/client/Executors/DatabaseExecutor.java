package ru.renue.fts.asktt.client.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.renue.fts.asktt.client.data.persistence.MsgInformationRepository;

/**
 * Created by disap on 01.08.2017.
 */
@Component
public class DatabaseExecutor {
    @Autowired
    private MsgInformationRepository msgInformationRepository;

}
