package ru.renue.fts.asktt.client.hessian.api;

/**
 * Created by disap on 27.07.2017.
 */
public interface IChecker {
    /**
     * Проверка непрочитанных сообщений в базе.
     * Просмотр идет по имени очереди имя которой передается
     * от клиента
     * @param queueName
     * @return Тело сообщения
     */
    byte[] checkByMessage(String queueName);
}
