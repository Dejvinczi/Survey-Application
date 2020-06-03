package pl.surveyapplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.surveyapplication.model.User;
import java.util.List;
import java.util.Optional;

/**
 * @author Dawid
 * @version 1.0
 * Interface repozytoryjny użytkowników. Pozwala na interakcje pomiędzy aplikacją a bazą danych.
 * */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    Optional<User> findByEmail(String userEmail);
}
