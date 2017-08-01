package ru.renue.fts.asktt.client.hessian.implementation;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.renue.fts.asktt.client.hessian.api.Calculation;

/**
 * Created by disap on 19.07.2017.
 */
@Component
public class CalculationImpl implements Calculation {
    private Logger logger = (Logger) LoggerFactory.getLogger(CalculationImpl.class);
    /**
     * Реализация интерфейся Calculation.
     * @param number Просто число
     * @return Возвращает куб числа
     */
    public int cube(final int number) {
        logger.info("Hessian API. вызван метод : " + CalculationImpl.class + ". Метод: cube(final int number)");
        return number * number * number;
    }
}
