package ru.renue.fts.asktt.client.testapi.rest;

/**
 * Created by disap on 18.07.2017.
 */
public class SimpleDataSource {
    private final long id;
    private final String content;

    public SimpleDataSource(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
