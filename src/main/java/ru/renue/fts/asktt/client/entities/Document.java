package ru.renue.fts.asktt.client.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by disap on 20.07.2017.
 */
@Data
@Entity
@Table(name = "received_documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;

    public Document() {
    }

    public Document(final String name) {
        this.name = name;
    }

    public Document(final String name,final int id) {
        this.name = name;
        this.id = id;
    }

}
