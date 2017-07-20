package ru.renue.fts.asktt.client.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by disap on 20.07.2017.
 */
@Entity
@Table(name = "received_documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;

    public Document(){}
    public Document(String name){
        this.name = name;
    }
    public Document(String name, int id){
        this.name = name;
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getname(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
