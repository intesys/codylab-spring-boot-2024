package it.intesys.academy.controller.mvc;

import it.intesys.academy.dto.UserDTO;
import it.intesys.academy.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String getUser(@PathParam("id") Long id, Model model) {

        model.addAttribute("user", new UserDTO());

        return "user";

    }



}
