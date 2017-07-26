package ru.renue.fts.asktt.client.hessian.service;

/**
 * Created by disap on 26.07.2017.
 */
public interface IMqSender {
    /**
     * Интерфейс для Hessian.
     * перенаправляет отправку сообщения от
     * клиента на сервис и в mq
     * @param queueName
     * @param array
     */
    void sendmessage(String queueName, byte[] array);
}
