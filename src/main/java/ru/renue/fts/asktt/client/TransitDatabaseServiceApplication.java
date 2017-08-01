package ru.renue.fts.asktt.client;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
        Logger logger = (Logger) LoggerFactory.getLogger(TransitDatabaseServiceApplication.class);
        logger.info("Application started");
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
        ExternalLibraryInitializer hacker = new ExternalLibraryInitializer(System.getProperty("mq.libraries.path"));
        hacker.init();
        SpringApplication.run(TransitDatabaseServiceApplication.class, args);
    }
}
