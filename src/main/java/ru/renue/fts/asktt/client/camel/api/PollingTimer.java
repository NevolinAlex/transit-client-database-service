package ru.renue.fts.asktt.client.camel.api;

/**
 * Created by disap on 01.08.2017.
 */

import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;

/**
 * Отслеживает время последнего обращения клиента к сервису.
 */
@Component
public class PollingTimer {
    private static long timeOfAging = 3600*3;
    private HashMap<String, Date>  lastPollingTime = new HashMap<>();

    /**
     * Время последнего обращения клиента к сервису.
     * @param routeId
     * @return
     */
    public Date getLastPollingTime(final String routeId){
        return lastPollingTime.get(routeId);
    }

    /**
     * Обновление времени обращения клиента к сервису.
     * @param routeId
     * @return
     */
    public void updateLastPollingTime(final String routeId){
        lastPollingTime.put(routeId, new Date());
    }

    /**
     * Удаление прослушиваемоей очереди.
     * Если последнее время обращения клиента
     * Бельше чем заданное.
     * @param routeId
     * @return
     */
}
