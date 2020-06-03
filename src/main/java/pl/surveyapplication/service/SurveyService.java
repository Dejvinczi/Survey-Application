package pl.surveyapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.surveyapplication.exception.SurveyNotFoundException;
import pl.surveyapplication.model.Survey;
import pl.surveyapplication.repository.SurveyRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Dawid
 * @version 1.0
 * Klasa serwisów ankiet.
 * */
@Component
public class SurveyService {
    /**
     * Zmienna przechowuje repozytorium ankiet
     * */
    @Autowired
    private SurveyRepository surveyRepository;

    /**
     * Metoda dodaje ankiete do bazy danych.
     * @param survey połączenie.
     * @return zwraca obiekt ankiety
     * */
    public Survey addSurvey(Survey survey){
        return surveyRepository.save(survey);
    }

    /**
     * Metoda pobiera wszystkie ankiety z bazy danych.
     * @return zwraca liste obiektów ankiet
     * */
    public List<Survey> getSurveys(){
        return surveyRepository.findAll();
    }

    /**
     * Metoda pobiera ankiete o podanym ID z bazy danych.
     * @param surveyId ID ankiety.
     * @return zwraca obiekt ankiety
     * */
    public Survey getSurvey(Long surveyId){
        Optional<Survey> optionalSurvey = surveyRepository.findById(surveyId);
        if(!optionalSurvey.isPresent())
            throw new SurveyNotFoundException("Survey of this ID is not available...");
        return optionalSurvey.get();
    }
}
