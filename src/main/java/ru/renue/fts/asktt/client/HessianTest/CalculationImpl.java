package ru.renue.fts.asktt.client.HessianTest;

import com.caucho.hessian.server.HessianServlet;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

/**
 * Created by disap on 19.07.2017.
 */
@Component
public class CalculationImpl implements Calculation{
    public int cube(int number) {
        return number*number*number;
    }
}
