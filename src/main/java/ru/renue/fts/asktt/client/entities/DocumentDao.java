package ru.renue.fts.asktt.client.entities;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by disap on 20.07.2017.
 */
@Transactional
public interface DocumentDao extends CrudRepository<Document, Integer>{

}
