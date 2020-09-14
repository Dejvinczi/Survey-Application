package pl.surveyapplication.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.surveyapplication.model.*;
import pl.surveyapplication.service.ConnectionService;
import pl.surveyapplication.service.SurveyService;
import pl.surveyapplication.service.UserService;

import java.util.List;

/**
 * @author Dawid
 * @version 1.0
 * Klasa kontrollera w której zarządzamy przypisaniami user - survey oraz stworzyć takiego przypisanie.
 * */
@Controller
@RequestMapping(value = "/admin/connections")
public class ConnectionController {
        /**
        * Zmienna odwolujaca się do serwisów połączeń
        * */
        @Autowired
        ConnectionService connectionService;
        /**
         * Zmienna odwolujaca się do serwisów użytkowników
         * */
        @Autowired
        UserService userService;
        /**
         * Zmienna odwolujaca się do serwisów ankiet
         * */
        @Autowired
        SurveyService surveyService;

        /**
         * Metoda pozwala na zwrocenie nam wszystkich dotychczasowych polaczen user - survey.
         * @param model Model do tworzenia obiektów w html
         * @return String html, strone w wszystkimi połączeniami user - survey
         * */
        @RequestMapping
        public String getAllConnections(Model model){
            List<Connection> list = connectionService.getAllConnections();

            model.addAttribute("connections", list);
            return "connection/list-connections";
        }

        /**
         * Metoda pozwala na stworzenie polaczenia user - survey
         * @param model Model do tworzenia obiektów w html
         * @return String html, strone w której możeby dodać połączenie user - survey
         * */
        @RequestMapping(path = {"/add"})
        public String addConnection(Model model){
            List<User> users = userService.getAllUsers();
            List<Survey> surveys = surveyService.getSurveys();

            model.addAttribute("userList", users);
            model.addAttribute("surveyList", surveys);
            model.addAttribute("connection", new Connection());

            return "connection/add-connection";
        }

        /**
         * Metoda zapisuje do bazy danych polaczenie.
         * @return String html, strone ze wszystkimi polaczeniami user - survey
         * */
        @RequestMapping(path = "/assign", method = RequestMethod.POST)
        public String createConnection(Connection connection) {
            connectionService.addConnection(connection);
            return "redirect:/admin/connections";
        }
}

