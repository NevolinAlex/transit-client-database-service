package ru.renue.fts.asktt.client;

import javafx.application.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.renue.fts.asktt.utils.ExternalLibraryInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.io.IOException;

/**
 * Загружает приложение через native Tomcat.
 */
public class WebXml extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        ExternalLibraryInitializer hacker = new ExternalLibraryInitializer(System.getProperty("custom.libraries.path"));
        try {
            hacker.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return application.sources(Application.class);
    }

    @Override
    public void onStartup(final ServletContext container) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("ru.renue.fts.asktt.utils.outdate");

        container.addListener(new ContextLoaderListener(context));

        ServletRegistration.Dynamic dispatcher = container.addServlet("hessian", new DispatcherServlet(context));

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}