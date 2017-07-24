package ru.renue.fts.asktt.client.hessian.service;

import org.springframework.stereotype.Component;

/**
 * Created by disap on 19.07.2017.
 */
@Component
public class CalculationImpl implements Calculation {
    /**
     * Реализация интерфейся Calculation.
     * @param number Просто число
     * @return Возвращает куб числа
     */
    public int cube(final int number) {
        return number * number * number;
    }
}
