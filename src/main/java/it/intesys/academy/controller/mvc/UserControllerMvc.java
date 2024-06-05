package it.intesys.academy.controller.mvc;

import it.intesys.academy.service.PartialDayTimeOffService;
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

    private final PartialDayTimeOffService partialDayTimeOffService;

    public UserControllerMvc(UserService userService, PartialDayTimeOffService partialDayTimeOffService) {
        this.userService = userService;
        this.partialDayTimeOffService = partialDayTimeOffService;
    }

    @GetMapping("/all")
    public String getUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "users";

    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") String id, Model model) {

        model.addAttribute("user", userService.getUser( Long.valueOf(id) ));

        model.addAttribute("partialDayTimeOffs", partialDayTimeOffService.getPartialDayTimeoff( Long.valueOf(id) ));

        return "user";

    }

    @GetMapping("/partial-time-off/delete/{id}")
    public String deletePartialTimeOff(@PathVariable("id") String idPartialTimeOff, Model model) {

        partialDayTimeOffService.deletePartialDayTimeOff( Long.valueOf(idPartialTimeOff));

        return "message";

    }



}
