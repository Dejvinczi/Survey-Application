package pl.surveyapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.surveyapplication.model.User;
import pl.surveyapplication.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService service;

    @RequestMapping
    public String getAllUsers(Model model)
    {
        List<User> list = service.getAllUsers();

        model.addAttribute("users", list);
        return "list-users";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editUserById(Model model, @PathVariable("id") Optional<Long> id)
    {
        if (id.isPresent()) {
            User entity = service.getUserById(id.get());
            model.addAttribute("user", entity);
        }
        return "edit-user";
    }

    @RequestMapping(path = {"/add"})
    public String addUserBy(Model model)
    {
            model.addAttribute("user", new User());
        return "add-user";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteUserById(Model model, @PathVariable("id") Long id)
    {
        service.deleteUserById(id);
        return "redirect:/users";
    }

    @RequestMapping(path = "/createUser", method = RequestMethod.POST)
    public String createOrUpdateUser(User user)
    {
        if(user.getRoles() == null) user.setRoles("USER");
        user.setActive(true);
        service.createOrUpdateUser(user);
        return "redirect:/users";
    }

}
