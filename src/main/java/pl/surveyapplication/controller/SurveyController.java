package pl.surveyapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.surveyapplication.model.Survey;
import pl.surveyapplication.service.SurveyService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/surveys")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @RequestMapping
    public String getAllSurveys(Model model)
    {
        List<Survey> list = surveyService.getSurveys();

        model.addAttribute("surveys", list);
        return "list-surveys";
    }

    @RequestMapping(path = {"/add"})
    public String addSurveyBy(Model model){
        model.addAttribute("survey",new Survey());
        return "add-survey";
    }

    @RequestMapping (path = {"/show", "/show/{id}"})
    public String getSurveyById(Model model, @PathVariable("id") Optional<Long> id){
        if (id.isPresent()){
            Survey entity = surveyService.getSurvey(id.get());
            model.addAttribute("survey", entity);
        }
        return "show-survey";
    }

    @RequestMapping(path = "/createSurvey", method = RequestMethod.POST)
    public String createOrUpdateUser(Survey survey)
    {
        surveyService.addSurvey(survey);
        return "redirect:/surveys";
    }
}
