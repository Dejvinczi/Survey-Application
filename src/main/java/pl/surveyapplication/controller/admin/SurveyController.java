package pl.surveyapplication.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.surveyapplication.model.Answer;
import pl.surveyapplication.model.Question;
import pl.surveyapplication.model.Survey;
import pl.surveyapplication.service.SurveyService;
import pl.surveyapplication.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Dawid
 * @version 1.0
 * Klasa kontrollera w której można zarządzać naszymi ankietami.
 * */
@Controller
@RequestMapping(value = "/admin/surveys")
public class SurveyController {

    /**
     * Zmienna odwolujaca się do serwisów ankiet
     * */
    @Autowired
    private SurveyService surveyService;

    /**
     * Zmienna odwolujaca się do serwisów użytkowników
     * */
    @Autowired
    private UserService userService;

    /**
     * Metoda pozwala na zwrocenie nam wszystkich ankiet.
     * @param model Model do tworzenia obiektów w html
     * @return String html, strone z wszystkimi ankietami.
     * */
    @RequestMapping
    public String getAllSurveys(Model model){
        List<Survey> list = surveyService.getSurveys();

        model.addAttribute("surveys", list);
        return "survey/list-surveys";
    }

    /**
     * Metoda pozwala stworzenie nowej ankiety.
     * @param model Model do tworzenia obiektów w html
     * @return String html, strone z kreatorem ankiety.
     * */
    @RequestMapping(path = {"/add"})
    public String addSurvey(Model model){
        model.addAttribute("survey",new Survey());
        model.addAttribute("question",new Question());
        model.addAttribute("answer", new Answer());
        model.addAttribute("allAnswers", new ArrayList<>());
        return "survey/add-survey";
    }

    /**
     * Metoda pozwala na podglad ankiety o danym ID.
     * @param model Model do tworzenia obiektów w html
     * @param id Id danej ankiety
     * @return String html, strone z konkretna ankieta o podanym ID.
     * */
    @RequestMapping (path = {"/show", "/show/{id}"})
    public String getSurveyById(Model model, @PathVariable("id") Optional<Long> id){
        if (id.isPresent()){
            Survey entity = surveyService.getSurvey(id.get());
            model.addAttribute("survey", entity);
        }
        return "survey/show-survey";
    }

//    @RequestMapping(path = "/createSurvey", method = RequestMethod.GET)
//    public String createSurvey(){
//        Survey survey = new Survey();
//        survey.setSurveyName("Ankieta o zdrowiu");
//        List<Question> lista = new ArrayList<>();
//        for(int i=0; i<10; i++){
//            lista.add(new Question());
//            lista.get(i).setQuestion("Oto pytanie nr."+i);
//            List<Answer> lista2 = new ArrayList<>();
//                for(int j=0; j<4; j++){
//                    lista2.add(new Answer());
//                    lista2.get(j).setAnswer("Odpowiedz nr."+j);
////                    lista2.get(i).setCheck(false);
//                }
//            lista.get(i).setAnswers(lista2);
//        }
//        survey.setQuestions(lista);
//
//        surveyService.addSurvey(survey);
//        return null;
//    }
}
