package ru.renue.fts.asktt.client.TestAPI.RESTful;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by disap on 18.07.2017.
 */
@RestController
public class RestControllerExample {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @RequestMapping("/greeting")
    public SimpleDataSource greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new SimpleDataSource(counter.incrementAndGet(),
                String.format(template, name));
    }
}
