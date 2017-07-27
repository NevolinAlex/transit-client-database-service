package ru.renue.fts.asktt.client.hessian.api;

/**
 * Created by disap on 19.07.2017.
 */
public interface Calculation {
    /**
     * Интерфейс для HessianApi.
     * Должен быть определен на стороне клиента (и сервера тоже).
     * @param number Целое число
     * @return Куб числа
     */
    int cube(int number);
}