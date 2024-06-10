package it.intesys.academy.controller.mvc;

import it.intesys.academy.dto.FullDayTimeOffDTO;
import it.intesys.academy.dto.PartialDayTimeOffDTO;
import it.intesys.academy.dto.TimeRangeDTO;
import it.intesys.academy.service.FullDayTimeOffService;
import it.intesys.academy.service.PartialDayTimeOffService;
import it.intesys.academy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/mvc/user")
public class UserControllerMvc {

    private final UserService userService;

    private final PartialDayTimeOffService partialDayTimeOffService;
    private final FullDayTimeOffService fullDayTimeOffService;

    public UserControllerMvc(UserService userService, PartialDayTimeOffService partialDayTimeOffService, FullDayTimeOffService fullDayTimeOffService) {
        this.userService = userService;
        this.partialDayTimeOffService = partialDayTimeOffService;
        this.fullDayTimeOffService = fullDayTimeOffService;
    }

    @GetMapping("/all")
    public String getUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "users";

    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") String id, Model model) {

        model.addAttribute("user", userService.getUser(Long.parseLong(id)));
        model.addAttribute("fullDayTimeOffs", fullDayTimeOffService.getFullDayTimeoff(Long.parseLong(id)));
        model.addAttribute("partialDayTimeOffs", partialDayTimeOffService.getPartialDayTimeoff(Long.parseLong(id)));

        return "user";

    }

//    FullDayTimeOff

    @PostMapping("/{userId}/full-day-timeoff/{fullDayTimeOffId}")
    public String addFullDayTimeoff(
            @PathVariable("userId") long userId,
            @PathVariable("fullDayTimeOffId") long fullDayTimeOffId,
            String from,
            String to
    ) {

        FullDayTimeOffDTO fullDayTimeOffDTO = new FullDayTimeOffDTO();
        fullDayTimeOffDTO.setId(fullDayTimeOffId); //chiave
        fullDayTimeOffDTO.setFrom(from);
        fullDayTimeOffDTO.setTo(to);

        fullDayTimeOffService.save(fullDayTimeOffDTO, userId);

        return "redirect:/mvc/user/" + userId;
    }

    @GetMapping("/full-day-timeoff/delete/{id}/user/{idUser}")
    public String deleteFullTimeOff(@PathVariable("id") String idFullTimeOff,
                                    @PathVariable("idUser") String userId) {

        fullDayTimeOffService.deleteFullDayTimeOff(Long.parseLong(idFullTimeOff));

        return "redirect:/mvc/user/" + userId;

    }

    @GetMapping("full-day-timeoff/edit/{id}/user/{userId}")
    public String editFullDayTimeOff(@PathVariable("id") String id, @PathVariable("userId") String userId, Model model) {
        if (Long.parseLong(id) == 0) {
            model.addAttribute("fullDayTimeOff", new FullDayTimeOffDTO());
        } else {
            model.addAttribute("fullDayTimeOff", fullDayTimeOffService.getFullDayTimeOffById(Long.parseLong(id)));
        }

        model.addAttribute("userId", userId);
        return "fullDayTimeOffForm";
    }

//    PartialDayTimeOff

    @GetMapping("/{userId}/partial-day-timeoff-form")
    public String partialDayTimeOffForm(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "PartialDayTimeOffForm";
    }

    @PostMapping("/{userId}/partial-day-timeoff/add")
    public String addPartialDayTimeOff(
            @PathVariable("userId") long userId,
            String date,
            String morningFrom,
            String morningTo,
            String afternoonFrom,
            String afternoonTo
    ) {

        //Save date
        TimeRangeDTO timeRangeDTO1 = new TimeRangeDTO();
        timeRangeDTO1.setFrom(morningFrom);
        timeRangeDTO1.setTo(morningTo);
        TimeRangeDTO timeRangeDTO2 = new TimeRangeDTO();
        timeRangeDTO2.setFrom(afternoonFrom);
        timeRangeDTO2.setTo(afternoonTo);

        PartialDayTimeOffDTO partialDayTimeOffDTO = new PartialDayTimeOffDTO();
        partialDayTimeOffDTO.setDate(date);
        partialDayTimeOffDTO.setTimeRangeDTOList(List.of(timeRangeDTO1, timeRangeDTO2));

        partialDayTimeOffService.save(partialDayTimeOffDTO, userId);

        return "redirect:/mvc/user/" + userId;
    }

    @GetMapping("/partial-day-timeoff/delete/{id}/user/{idUser}")
    public String deletePartialTimeOff(@PathVariable("id") String idPartialTimeOff,
                                       @PathVariable("idUser") String userId) {

        partialDayTimeOffService.deletePartialDayTimeOff(Long.parseLong(idPartialTimeOff));

        return "redirect:/mvc/user/" + userId;

    }
}
