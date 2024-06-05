package it.intesys.academy.controller.mvc;

import it.intesys.academy.dto.PartialDayTimeOffDTO;
import it.intesys.academy.dto.TimeRangeDTO;
import it.intesys.academy.service.PartialDayTimeOffService;
import it.intesys.academy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

    @GetMapping("/partial-day-timeoff/delete/{id}/user/{idUser}")
    public String deletePartialTimeOff(@PathVariable("id") String idPartialTimeOff,
                                       @PathVariable("idUser") String userId) {

        partialDayTimeOffService.deletePartialDayTimeOff( Long.valueOf(idPartialTimeOff));

        return "redirect:/mvc/user/" + String.valueOf(userId);

    }

    @PostMapping("/partial-day-timeoff/add")
    public String addPartialDayTimeoff() {

        long userId = 1l;

        //Save date
        TimeRangeDTO timeRangeDTO1 = new TimeRangeDTO();
        timeRangeDTO1.setFrom("10:00");
        timeRangeDTO1.setTo("11:00");
        TimeRangeDTO timeRangeDTO2 = new TimeRangeDTO();
        timeRangeDTO2.setFrom("15:00");
        timeRangeDTO2.setTo("18:00");

        PartialDayTimeOffDTO partialDayTimeOffDTO = new PartialDayTimeOffDTO();
        partialDayTimeOffDTO.setDate("15/09/2024");
        partialDayTimeOffDTO.setTimeRangeDTOList(List.of(timeRangeDTO1, timeRangeDTO2));

        partialDayTimeOffService.save(partialDayTimeOffDTO, userId);

        return "redirect:/mvc/user/" + String.valueOf(userId);
    }



}
