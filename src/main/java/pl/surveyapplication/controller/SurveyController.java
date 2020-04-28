package pl.surveyapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.surveyapplication.model.Survey;
import pl.surveyapplication.service.SurveyService;

import java.util.List;

@Controller
@RequestMapping(value = "/surveys")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @PostMapping()
    public Survey addSurvey(@RequestBody Survey survey){
        return surveyService.addSurvey(survey);
    }

    @RequestMapping
    public String getAllSurveys(Model model)
    {
        List<Survey> list = surveyService.getSurveys();

        model.addAttribute("surveys", list);
        return "list-surveys";
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
