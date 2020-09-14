package pl.surveyapplication.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.surveyapplication.model.*;
import pl.surveyapplication.service.ConnectionService;
import pl.surveyapplication.service.SurveyMagazinService;
import pl.surveyapplication.service.UserService;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Dawid
 * @version 1.0
 * Klasa kontrollera w której można zarządzać przypisanymi ankietami danego użytkownika.
 */
@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/user/surveys")
public class CompleteSurveyController {

    private final Logger logger = LoggerFactory.getLogger(CompleteSurveyController.class);
    @Autowired
    ConnectionService connectionService;
    /**
     * Zmienna odwolujaca się do serwisów zakończonych ankiet
     */
    @Autowired
    SurveyMagazinService surveyMagazinService;

    @Autowired
    UserService userService;

    @RequestMapping
    public java.lang.String showMySurveys(Model model, Authentication authentication) {
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        List<Connection> mySurveys = connectionService.getConnectionsByUserId(userService.getUserById(myUserDetails.getId()));

        model.addAttribute("mySurveys", mySurveys);
        return "completing/my-surveys";
    }

    @GetMapping("/try/{id}")
    public ResponseEntity<?> getDataForAngular(@PathVariable Long id) {
        Connection connection = connectionService.getConnection(id);
        FilledSurvey filledSurvey = connection.getSurvey().getTemplate();
        connection.setDevoted(true);
        connectionService.updateConnection(connection);

        return ResponseEntity.ok(filledSurvey);
    }

    @PostMapping("/finish-from-angular")
    public ResponseEntity<?> receiveDataFromAngular(@RequestBody FilledSurvey filledSurvey) {
        StringBuilder sb = new StringBuilder();
        for (FilledQuestion question : filledSurvey.getFilledQuestions()) {
            for (FilledAnswer answer : question.getFilledAnswers()) {
                if (answer.isCheck()){
                    sb.append(answer.getAnswer());
                    System.out.println(answer.getAnswer());
                }
            }
        }

        LocalDateTime date = LocalDateTime.now();
        sb.append(date);
        String hash = sb.toString();
        hash = Base64.getEncoder().encodeToString(sb.toString().getBytes());
        filledSurvey.setHash(hash);

        Map<String, String> hashValue = new HashMap<>();
        hashValue.put("hash", hash);
        surveyMagazinService.addSurveyToMagazin(filledSurvey);

        return new ResponseEntity<>(hashValue, HttpStatus.CREATED);
    }

    @GetMapping("/getHash/{hash}")
    public String getHash(@PathVariable String hash, Model model) {
        String token = hash;
        model.addAttribute("token", token);

        return "completing/finish";
    }

    @RequestMapping("/getMySurvey")
    public String getMySurvey(Model model)
    {
        String token = "";
        model.addAttribute("token", token);
        return "completing/show-my-survey";
    }

    /**
     * Metoda zwraca ankiete o danym tokenie.
     *
     * @param model Model do tworzenia obiektów w html
     * @return String html, strone do podglądu uzupełnionej ankiety.
     */

    @RequestMapping(value = "/showMySurvey", method=RequestMethod.POST)
    public String showMySurvey(@ModelAttribute(value="token") String token, Model model) {
        StringBuilder sb = new StringBuilder();
        sb.append(token);
        sb.append("==");
        FilledSurvey filledSurvey = surveyMagazinService.getSurveyByHash(sb.toString());
        model.addAttribute("survey", filledSurvey);
        return "completing/survey";
    }
}



