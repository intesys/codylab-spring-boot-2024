package it.intesys.academy.controller.mvc;

import it.intesys.academy.dto.FullDayTimeOffDTO;
import it.intesys.academy.dto.PartialDayTimeOffDTO;
import it.intesys.academy.dto.TimeRangeDTO;
import it.intesys.academy.service.FullDayTimeOffService;
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
    private final FullDayTimeOffService fullDayTimeOffService;
    private final PartialDayTimeOffService partialDayTimeOffService;

    public UserControllerMvc(UserService userService, FullDayTimeOffService fullDayTimeOffService, PartialDayTimeOffService partialDayTimeOffService) {
        this.userService = userService;
        this.fullDayTimeOffService = fullDayTimeOffService;
        this.partialDayTimeOffService = partialDayTimeOffService;
    }

    @GetMapping("/all")
    public String getUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "users";

    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") String id, Model model) {

        model.addAttribute("user", userService.getUser( Long.parseLong(id) ));

        model.addAttribute("partialDayTimeOffs", partialDayTimeOffService.getPartialDayTimeoff( Long.parseLong(id) ));

        model.addAttribute("fullDayTimeOffs", fullDayTimeOffService.getFullDayTimeoff(Long.parseLong(id)));

        return "user";

    }

    @GetMapping("/partial-day-timeoff/delete/{id}/user/{idUser}")
    public String deletePartialTimeOff(@PathVariable("id") String idPartialTimeOff,
                                       @PathVariable("idUser") String userId) {

        partialDayTimeOffService.deletePartialDayTimeOff( Long.parseLong(idPartialTimeOff));

        return "redirect:/mvc/user/" + userId;

    }

    @GetMapping("/partial-day-timeoff/get-form/{idUser}")
    public String addPartialForm(@PathVariable("idUser") String userId, Model model) {

//        long userId = 1l;
//
//        //Save date
//        TimeRangeDTO timeRangeDTO1 = new TimeRangeDTO();
//        timeRangeDTO1.setFrom("10:00");
//        timeRangeDTO1.setTo("11:00");
//        TimeRangeDTO timeRangeDTO2 = new TimeRangeDTO();
//        timeRangeDTO2.setFrom("15:00");
//        timeRangeDTO2.setTo("18:00");
//
//        PartialDayTimeOffDTO partialDayTimeOffDTO = new PartialDayTimeOffDTO();
//        partialDayTimeOffDTO.setDate("15/09/2024");
//        partialDayTimeOffDTO.setTimeRangeDTOList(List.of(timeRangeDTO1, timeRangeDTO2));
//
//        partialDayTimeOffService.save(partialDayTimeOffDTO, userId);
//
//        return "redirect:/mvc/user/" + String.valueOf(userId);

        model.addAttribute("userId", userId);

        return "partialDayForm";
    }

    @PostMapping("/partial-day-timeoff/add/{idUser}")
    public String savePartialDayTimeoff(@PathVariable("idUser") String userId,
                                        String date,
                                        String mStart,
                                        String mEnd,
                                        String pStart,
                                        String pEnd) {

        TimeRangeDTO timeRangeDTO1 = new TimeRangeDTO();
        timeRangeDTO1.setFrom(mStart);
        timeRangeDTO1.setTo(mEnd);
        TimeRangeDTO timeRangeDTO2 = new TimeRangeDTO();
        timeRangeDTO2.setFrom(pStart);
        timeRangeDTO2.setTo(pEnd);

        PartialDayTimeOffDTO partialDayTimeOffDTO = new PartialDayTimeOffDTO();
        partialDayTimeOffDTO.setDate(date);
        partialDayTimeOffDTO.setTimeRangeDTOList(List.of(timeRangeDTO1, timeRangeDTO2));

        partialDayTimeOffService.save(partialDayTimeOffDTO, Long.parseLong(userId));

        return "redirect:/mvc/user/" + userId;

    }

    @GetMapping("/full-day-timeoff/get-form/{idUser}")
    public String addFullForm(@PathVariable("idUser") String userId, Model model) {

        model.addAttribute("userId", userId);

        return "fullDayForm";
    }

    @PostMapping("/full-day-timeoff/add/{idUser}")
    public String savePartialDayTimeoff(@PathVariable("idUser") String userId,
                                        String dateStart,
                                        String dateEnd
                                        ) {

        FullDayTimeOffDTO fullDayTimeOffDTO = new FullDayTimeOffDTO();
        fullDayTimeOffDTO.setDateStart(dateStart);
        fullDayTimeOffDTO.setDateEnd(dateEnd);

        fullDayTimeOffService.save(fullDayTimeOffDTO, Long.parseLong(userId));


        return "redirect:/mvc/user/" + userId;
    }

    @GetMapping("/full-day-timeoff/delete/{id}/user/{idUser}")
    public String deleteFullTimeOff(@PathVariable("id") String idFullTimeOff,
                                    @PathVariable("idUser") String userId) {

        fullDayTimeOffService.deleteFullDayTimeOff(Long.parseLong(idFullTimeOff));

        return "redirect:/mvc/user/" + userId;

    }

    @GetMapping("/full-day-timeoff/edit/{id}/user/{idUser}")
    public String editFullTimeOff(@PathVariable("id") String idFullTimeOff,
                                  @PathVariable("idUser") String userId,
                                  Model model) {
        FullDayTimeOffDTO fullDayTimeOffDTO = fullDayTimeOffService.getFullDayTimeOffById(Long.parseLong(idFullTimeOff));

        model.addAttribute("fullDayTimeOffDTO", fullDayTimeOffDTO);
        model.addAttribute("userId", userId);

        return "fullDayEditForm";
    }

    @PostMapping("/full-day-timeoff/edit/{id}/user/{idUser}")
    public String editPartialDayTimeoff(@PathVariable("id") String id,
                                        @PathVariable("idUser") String userId,
                                        String dateStart,
                                        String dateEnd
    ) {

        FullDayTimeOffDTO fullDayTimeOffDTO = new FullDayTimeOffDTO();
        fullDayTimeOffDTO.setId(Long.parseLong(id));
        fullDayTimeOffDTO.setDateStart(dateStart);
        fullDayTimeOffDTO.setDateEnd(dateEnd);

        fullDayTimeOffService.save(fullDayTimeOffDTO, Long.parseLong(userId));


        return "redirect:/mvc/user/" + userId;
    }
}
