package it.intesys.academy.controller.mvc;

import it.intesys.academy.service.TimeOffService;
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
    private final TimeOffService timeOffService;

    public UserControllerMvc(UserService userService, TimeOffService timeOffService) {
        this.userService = userService;
        this.timeOffService = timeOffService;
    }

    @GetMapping("/all")
    public String getUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "users";

    }

    @GetMapping("/{id}")
    public String getUser(Model model, @PathVariable("id") Long id) {

        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("fullDayTimeOff", timeOffService.getFullDayTimeOffByUserId(id));
        model.addAttribute("partialDayTimeOff", timeOffService.getPartialDayTimeOffByUserId(id));

        return "user";

    }

    @GetMapping("/deletePartialDayTimeOffTimeRange/{id}")
    public String deletePartialDayTimeOffTimeRange(@PathVariable("id") Long id) {
        timeOffService.deletePartialDayTimeOffTimeRange(id);
        return "success";
    }

    @GetMapping("/deleteFullDayTimeOff/{id}")
    public String deleteFullDayTimeOff(@PathVariable("id") Long id) {
        timeOffService.deleteFullDayTimeOff(id);
        return "success";
    }

}
