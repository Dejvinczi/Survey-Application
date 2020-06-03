package pl.surveyapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.surveyapplication.exception.SurveyNotFoundException;
import pl.surveyapplication.model.FilledSurvey;
import pl.surveyapplication.repository.SurveyMagazinRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Dawid
 * @version 1.0
 * Klasa serwisów gotowych ankiet.
 * */
@Component
public class SurveyMagazinService {
    /**
     * Zmienna przechowuje repozytorium gotowych ankiet
     * */
    @Autowired
    private SurveyMagazinRepository surveyMagazinRepository;

    /**
     * Metoda dodaj gotową ankiete do bazy danych.
     * @param filledSurvey gotowa ankieta.
     * */
    public FilledSurvey addSurveyToMagazin(FilledSurvey filledSurvey){
        return surveyMagazinRepository.save(filledSurvey);
    }

    /**
     * Metoda zwraca liste gotowych ankiet w naszej bazie danych.
     * @return connections lista gotowych ankiet.
     * */
    public List<FilledSurvey> getAllSurveysFromMagazin(){
        return surveyMagazinRepository.findAll();
    }

    /**
     * Metoda zwraca gotową ankietę o podanym tokenie.
     * @param hash token odnoszący się do ankiety.
     * @return filledSurvey gotowa ankieta.
     * */
    public FilledSurvey getSurveyByHash(String hash){
        Optional<FilledSurvey> optionalSurveyMagazin = surveyMagazinRepository.findByHash(hash);
        if(!optionalSurveyMagazin.isPresent())
            throw new SurveyNotFoundException("Survey of this HASH is not available...");
        return optionalSurveyMagazin.get();
    }
}
