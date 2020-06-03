package pl.surveyapplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.surveyapplication.model.Survey;

import java.util.List;

/**
 * @author Dawid
 * @version 1.0
 * Interface repozytoryjny magazynu wypełnionych ankiet. Pozwala na interakcje pomiędzy aplikacją a bazą danych.
 * */
@Repository
public interface SurveyRepository extends CrudRepository<Survey,Long> {
    @Override
    List<Survey> findAll();
}
