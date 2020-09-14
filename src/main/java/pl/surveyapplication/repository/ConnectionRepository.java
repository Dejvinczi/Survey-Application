package pl.surveyapplication.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.surveyapplication.model.Connection;
import pl.surveyapplication.model.User;

import java.util.List;

/**
 * @author Dawid
 * @version 1.0
 * Interface repozytoryjny połączeń. Pozwala na interakcje pomiędzy aplikacją a bazą danych.
 * */
@Repository
public interface ConnectionRepository extends CrudRepository<Connection, Long> {
    @Override
    List<Connection> findAll();

    List<Connection> findByUserId(User user);
}
