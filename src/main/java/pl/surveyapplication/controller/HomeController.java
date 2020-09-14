package pl.surveyapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.surveyapplication.model.MyUserDetails;
import pl.surveyapplication.model.User;
import pl.surveyapplication.service.UserService;

/**
 * @author Dawid
 * @version 1.0
 * Klasa kontrollera która zarządza panelami Home, Admin oraz User w zależności kto się loguje.
 * */
@Controller
@RequestMapping("/")
public class HomeController {
    /**
     * Zmienna odwolujaca się do serwisów użytkowników
     * */
    @Autowired
    UserService userService;

    /**
     * Metoda zwraca nam panel admin-panel jeżeli loguje się ADMIN lub user-panel jeżeli loguje się USER.
     * @param authentication Zmienna w której zapisane są inormacje o zalogowanym uzytkowniku
     * @return String html, panel danego użytkownika.
     * */
    @RequestMapping("/home")
    public String printHome(Authentication authentication)
    {
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        User user =  userService.getUserById(myUserDetails.getId());

        if(user.getRoles().equals("ADMIN"))
            return "panels/admin-panel";
        else if(user.getRoles().equals("USER"))
            return "panels/user-panel";
        else
            return "panels/home-panel";

    }

    /**
     * Metoda zwraca nam panel user-panel.
     * @param authentication Zmienna w której zapisane są inormacje o zalogowanym uzytkowniku
     * @return String html, panel użytkownika.
     * */
    @RequestMapping("/user/userPanel")
    public String printUserPanel(Authentication authentication)
    {
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        User user =  userService.getUserById(myUserDetails.getId());
        if(user.getRoles().equals("USER"))
            return "panels/user-panel";
        else return "panels/home-panel";
    }

    /**
     * Metoda zwraca nam panel admin-panel.
     * @param authentication Zmienna w której zapisane są inormacje o zalogowanym uzytkowniku
     * @return String html, panel admina.
     * */
    @RequestMapping("/admin/adminPanel")
    public String printAdminPanel(Authentication authentication)
    {
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        User user =  userService.getUserById(myUserDetails.getId());
        if(user.getRoles().equals("ADMIN"))
            return "panels/admin-panel";
        else return "panels/home-panel";
    }

    /**
     * Metoda zwraca nam panel home-panel.
     * @return String html, panel domowy.
     * */
    @RequestMapping()
    public String printHome()
    {
        return "panels/home-panel";
    }
}
