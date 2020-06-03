package pl.surveyapplication.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.surveyapplication.model.*;
import pl.surveyapplication.service.ConnectionService;
import pl.surveyapplication.service.SurveyMagazinService;
import pl.surveyapplication.service.UserService;


import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

/**
 * @author Dawid
 * @version 1.0
 * Klasa kontrollera w której można zarządzać przypisanymi ankietami danego użytkownika.
 * */
@Controller
@RequestMapping(value = "/user/surveys")
public class CompleteSurveyController {

    /**
     * Zmienna odwolujaca się do serwisów połączeń user - survey
     * */
    @Autowired
    ConnectionService connectionService;
    /**
     * Zmienna odwolujaca się do serwisów zakończonych ankiet
     * */
    @Autowired
    SurveyMagazinService surveyMagazinService;
    /**
     * Zmienna odwolujaca się do serwisów użytkowników
     * */
    @Autowired
    UserService userService;

    /**
     * Metoda pozwala na zwrocenie nam wszystkich ankiet które zostały przypisane zalogowanemu użytkownikowi.
     * @param model Model do tworzenia obiektów w html
     * @param authentication Zmienna w której zapisane są inormacje o zalogowanym uzytkowniku
     * @return String html, strone z wszystkimi ankietami użytkownika.
     * */
    @RequestMapping
    public String showMySurveys(Model model, Authentication authentication){
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        List<Connection> mySurveys = connectionService.getConnectionsByUserId(userService.getUserById(myUserDetails.getId()));

        model.addAttribute("mySurveys", mySurveys);
        return "completing/my-surveys";
    }

    /**
     * Metoda pozwala na wypełnienie ankiety z podanego ID polaczenia.
     * @param model Model do tworzenia obiektów w html
     * @param id ID danego połączenia user - survey
     * @return String html, strone do wypełniania ankiety.
     * */
    @RequestMapping(path = {"/try", "/try/{id}"})
    public String tryCompletingSurvey(Model model, @PathVariable("id") Optional<Long> id) {
            Connection connection = connectionService.getConnection(id.get());
            Survey templateSurvey = connection.getSurvey();

            FilledSurvey filledSurvey = new FilledSurvey();
            filledSurvey = templateSurvey.getTemplate();

            for(FilledQuestion filledQuestion: filledSurvey.getFilledQuestions()){
                FilledAnswer filledAnswer = filledQuestion.getFilledAnswer().get(0);
                filledAnswer.setCheck(true);
            }

            connectionService.updateConnection(connection);


            StringBuilder sb = new StringBuilder();
            for(FilledQuestion question : filledSurvey.getFilledQuestions()){
                sb.append(question.getQuestion());
                for(FilledAnswer answer : question.getFilledAnswer()){
                    if(answer.isCheck()) sb.append(answer.getAnswer());
                }
            }

            LocalDateTime date = LocalDateTime.now();
            sb.append(date);
            String hash = Base64.getEncoder().encodeToString(sb.toString().getBytes());
            filledSurvey.setHash(hash);


            surveyMagazinService.addSurveyToMagazin(filledSurvey);

            model.addAttribute("hash", hash);
            model.addAttribute("survey",filledSurvey);

        return "completing/completing";
    }

    /**
     * Metoda pozwala na wyszukanie nam ankiety o wprowadzonym tokenie ze strony.
     * @param model Model do tworzenia obiektów w html
     * @return String html, strone mozliwością wprowadzenia tokena.
     * */
    @RequestMapping(path = "/showMySurvey")
    public String getHash(Model model) {
        model.addAttribute("hash", new String());

        return "completing/show-my-survey";
    }

    /**
     * Metoda zwraca ankiete o danym tokenie.
     * @param model Model do tworzenia obiektów w html
     * @return String html, strone do podglądu uzupełnionej ankiety.
     * */
    @RequestMapping(path = "/survey")
    public String showSurvey(String hash, Model model) {
        FilledSurvey filledSurvey = surveyMagazinService.getSurveyByHash(hash);
        model.addAttribute("survey", filledSurvey);
        return "completing/survey";
    }
}



