package pl.surveyapplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.surveyapplication.model.FilledSurvey;

import java.util.List;
import java.util.Optional;

/**
 * @author Dawid
 * @version 1.0
 * Interface repozytoryjny ankiet. Pozwala na interakcje pomiędzy aplikacją a bazą danych.
 * */
@Repository
public interface SurveyMagazinRepository extends CrudRepository<FilledSurvey, Long>{
    @Override
    List<FilledSurvey> findAll();

    Optional<FilledSurvey> findByHash(String hash);
}
