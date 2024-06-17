package it.intesys.academy.controller.mvc;

import it.intesys.academy.dto.PartialDayTimeOffDTO;
import it.intesys.academy.dto.FullDayTimeOffDTO;
import it.intesys.academy.dto.TimeRangeDTO;
import it.intesys.academy.service.PartialDayTimeOffService;
import it.intesys.academy.service.FullDayTimeOffService;
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
    private final FullDayTimeOffService fullDayTimeOffService;

    public UserControllerMvc(UserService userService, PartialDayTimeOffService partialDayTimeOffService, FullDayTimeOffService fullDayTimeOffService) {
        this.userService = userService;
        this.partialDayTimeOffService = partialDayTimeOffService;
        this.fullDayTimeOffService = fullDayTimeOffService;
    }

    //prendo tutti gli users
    @GetMapping("/all")
    public String getUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "users";

    }

    //prendo solo uno user grazie al suo id, e ne restituisco ferie e permessi esistenti
    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") String id, Model model) {

        model.addAttribute("user", userService.getUser( Long.parseLong(id) ));

        model.addAttribute("partialDayTimeOffs", partialDayTimeOffService.getPartialDayTimeoff( Long.parseLong(id) ));

        model.addAttribute("fullDayTimeOffs", fullDayTimeOffService.getFullDayTimeoff( Long.parseLong(id) ));

        return "user";

    }


    //richiama il form
    @GetMapping("/{userId}/full-day-timeoff-form")
    public String fullDayTimeOffForm(@PathVariable("userId") Long userId,
                                     Model model){

        model.addAttribute("uderId", userId);

        return "fullDayTimeOffForm";
    }


    //prende i dati dal form
    @PostMapping("/{userId}/full-day-timeoff/add")
    public String addFullDayTimeOff(@PathVariable("userId") Long userId,
                                     String from,
                                     String to) {

        FullDayTimeOffDTO fullDayTimeOffDTO = new FullDayTimeOffDTO();
        fullDayTimeOffDTO.setFrom(from);
        fullDayTimeOffDTO.setTo(to);

        fullDayTimeOffService.save(fullDayTimeOffDTO, userId);


        return "redirect:/mvc/user/" + userId;
    }

    
    //elimina la feria (partendo dal suo id)
    @GetMapping("/full-day-timeoff/delete/{id}/user/{idUser}")
    public String deleteFullTimeOff(@PathVariable("id") String idFullTimeOff,
                                       @PathVariable("idUser") String userId) {

        fullDayTimeOffService.deleteFullDayTimeOff( Long.parseLong(idFullTimeOff));

        return "redirect:/mvc/user/" + userId;
    }


    //elimina il permesso (partendo dal suo id)
    @GetMapping("/partial-day-timeoff/delete/{id}/user/{idUser}")
    public String deletePartialTimeOff(@PathVariable("id") String idPartialTimeOff,
                                       @PathVariable("idUser") String userId) {

        partialDayTimeOffService.deletePartialDayTimeOff( Long.parseLong(idPartialTimeOff));

        return "redirect:/mvc/user/" + userId;

    }

    //aggiunge un nuovo permesso
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
        partialDayTimeOffDTO.setDate("16/09/2024");
        partialDayTimeOffDTO.setTimeRangeDTOList(List.of(timeRangeDTO1, timeRangeDTO2));

        partialDayTimeOffService.save(partialDayTimeOffDTO, userId);

        return "redirect:/mvc/user/" + String.valueOf(userId);
    }



}
