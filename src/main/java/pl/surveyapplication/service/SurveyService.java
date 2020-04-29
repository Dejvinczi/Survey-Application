package pl.surveyapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.surveyapplication.exception.SurveyNotFoundException;
import pl.surveyapplication.model.Survey;
import pl.surveyapplication.repository.SurveyRepository;

import java.util.List;
import java.util.Optional;

@Component
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    public Survey addSurvey(Survey survey){
        return surveyRepository.save(survey);
    }

    public List<Survey> getSurveys(){
        return surveyRepository.findAll();
    }

    public Survey getSurvey(Long surveyId){
        Optional<Survey> optionalSurvey = surveyRepository.findById(surveyId);
        if(!optionalSurvey.isPresent())
            throw new SurveyNotFoundException("Survey of this ID is not available...");
        return optionalSurvey.get();
    }

    public Survey updateSurvey(Long surveyId, Survey survey){
        Optional<Survey> optionalSurvey = surveyRepository.findById(surveyId);
        if(!optionalSurvey.isPresent())
            throw new SurveyNotFoundException("Survey of this ID is not available...");
        survey.setSurveyId(surveyId);
        return surveyRepository.save(survey);
    }

    public void deleteSurvey(Long surveyId){
        Optional<Survey> optionalSurvey = surveyRepository.findById(surveyId);
        if(!optionalSurvey.isPresent())
            throw new SurveyNotFoundException("Survey of this ID is not available...");
        surveyRepository.deleteById(surveyId);
    }

}
