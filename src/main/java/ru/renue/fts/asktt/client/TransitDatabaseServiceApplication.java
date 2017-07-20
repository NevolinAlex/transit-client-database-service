package ru.renue.fts.asktt.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.renue.fts.asktt.utils.ExternalLibraryInitializer;

import java.io.IOException;

@SpringBootApplication
public class TransitDatabaseServiceApplication {

	public static void main(String[] args) throws IOException {
		ExternalLibraryInitializer hacker = new ExternalLibraryInitializer(System.getProperty("mq.libraries.path"));
		hacker.init();
		SpringApplication.run(TransitDatabaseServiceApplication.class, args);
	}

//	@RestController
//	class GreetingController {
//
//		@RequestMapping("/hello/{name}")
//		String hello(@PathVariable String name) {
//			return "Hello, " + name + "!";
//		}
//	}
}
