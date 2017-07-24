package ru.renue.fts.asktt.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.renue.fts.asktt.utils.ExternalLibraryInitializer;

import java.io.IOException;

/**
 * Класс инициализации SpringBoot приложения.
 */
@SpringBootApplication
public class TransitDatabaseServiceApplication {

    /**
     * Точка входа программы.
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        ExternalLibraryInitializer hacker = new ExternalLibraryInitializer(System.getProperty("mq.libraries.path"));
        hacker.init();
        SpringApplication.run(TransitDatabaseServiceApplication.class, args);
    }
}
