package pl.surveyapplication.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.surveyapplication.model.User;
import pl.surveyapplication.service.ConnectionService;
import pl.surveyapplication.service.SurveyService;
import pl.surveyapplication.service.UserService;

import java.util.List;
import java.util.Optional;

/**
 * @author Dawid
 * @version 1.0
 * Klasa kontrollera w której można zarządzać użytkownikami.
 * */
@Controller
@RequestMapping("/admin/users")
public class UserController {

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
     * Zmienna odwolujaca się do serwisów połączeń user - survey
     * */
    @Autowired
    ConnectionService connectionService;

    /**
     * Metoda pozwala na zwrocenie nam wszystkich użytkowników.
     * @param model Model do tworzenia obiektów w html
     * @return String html, strone z wszystkimi użytkownikami.
     * */
    @RequestMapping
    public String getAllUsers(Model model)
    {
        List<User> list = userService.getAllUsers();

        model.addAttribute("users", list);
        return "user/list-users";
    }

    /**
     * Metoda pozwala na edytowanie nam użytkownika o podanym ID.
     * @param model Model do tworzenia obiektów w html
     * @param id ID danego użytkownika
     * @return String html, strone szablonem do edycji danego użytkownika.
     * */
    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editUserById(Model model, @PathVariable("id") Optional<Long> id)
    {
        if (id.isPresent()) {
            User entity = userService.getUserById(id.get());
            model.addAttribute("user", entity);
        }
        return "user/edit-user";
    }

    /**
     * Metoda pozwala na stworzenie nam nowego użytkownika.
     * @param model Model do tworzenia obiektów w html
     * @return String html, strone szablonem do tworzenia użytkownika.
     * */
    @RequestMapping(path = {"/add"})
    public String addUser(Model model)
    {
            model.addAttribute("user", new User());
        return "user/add-user";
    }

    /**
     * Metoda pozwala na usunięcie  użytkownika.
     * @param model Model do tworzenia obiektów w html
     * @param id ID danego użytkownika
     * @return String html, strone z wszystkimi użytkownikami.
     * */
    @RequestMapping(path = "/delete/{id}")
    public String deleteUserById(Model model, @PathVariable("id") Long id)
    {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }

    /**
     * Metoda pozwala na dodanie użytkownika do bazy.
     * @return String html, strone z wszystkimi użytkownikami.
     * */
    @RequestMapping(path = "/createUser", method = RequestMethod.POST)
    public String createOrUpdateUser(User user)
    {
        if(user.getRoles() == null) user.setRoles("USER");
        user.setActive(true);
        userService.createOrUpdateUser(user);
        return "redirect:/admin/users";
    }
}
