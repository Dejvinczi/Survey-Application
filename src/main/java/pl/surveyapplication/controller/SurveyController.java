package pl.surveyapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.surveyapplication.model.Survey;
import pl.surveyapplication.service.SurveyService;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/surveys")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @PostMapping
    public Survey addSurvey(@RequestBody Survey survey){
        return surveyService.addSurvey(survey);
    }

    @GetMapping
    public List<Survey> getSurveys(){
        return surveyService.getSurveys();
    }

    @GetMapping (value = "/{surveyId}")
    public Survey getSurvey(@PathVariable("surveyId") int surveyId){
        return surveyService.getSurvey(surveyId);
    }

    @PutMapping(value = "/{surveyId}")
    public Survey updateSurvey(@PathVariable("surveyId") int surveyId, @RequestBody Survey survey){
        return surveyService.updateSurvey(surveyId, survey);
    }

    @DeleteMapping(value = "/{surveyId}")
    public void deleteSurvey(@PathVariable("surveyId") int surveyId){
        surveyService.deleteSurvey(surveyId);
    }



}
