package it.intesys.academy.controller.mvc;

import it.intesys.academy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/mvc/user")
public class UserControllerMvc {

    private final UserService userService;

    public UserControllerMvc(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "users";

    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") String id, Model model) {

        model.addAttribute("user", userService.getUser( Long.valueOf(id) ));

        return "user";

    }



}
