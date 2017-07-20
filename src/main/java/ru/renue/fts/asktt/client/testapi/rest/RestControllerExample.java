package ru.renue.fts.asktt.client.testapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.renue.fts.asktt.client.entities.Document;
import ru.renue.fts.asktt.client.entities.DocumentDao;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by disap on 18.07.2017.
 */
@RestController
public class RestControllerExample {

    @Autowired
    private DocumentDao documentDao;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @RequestMapping("/create")
    public String create(@RequestParam(value="name", defaultValue="") String name) {
        if (name == ""){
            return "set parametr by example: http://localhost:8080/create?name=User";
        }
        Document document = null;
        try {
            document = new Document(name);
            documentDao.save(document);
        }
        catch (Exception ex){
            return "Error creating the user: " + ex.toString();
        }
        return "successful";
    }
    @RequestMapping("/get-by-id")
    public String getbyId(@RequestParam(value="id") Integer id){
        if (id == null){
            return "set parametr by example: http://localhost:8080/get-by-id?id=yourId";
        }
        Document document = null;
        try {
            document = documentDao.findOne(id);
        }
        catch (Exception ex){
            return "User not found";
        }
        return "The user id is: " + document.getname();
    }
}
