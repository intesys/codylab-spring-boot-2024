//package it.intesys.academy.controller.mvc;
//
//import it.intesys.academy.dto.FullDayTimeOffDTO;
//import it.intesys.academy.dto.PartialDayTimeOffDTO;
//import it.intesys.academy.dto.TimeRangeDTO;
//import it.intesys.academy.service.FullDayTimeOffService;
//import it.intesys.academy.service.PartialDayTimeOffService;
//import it.intesys.academy.service.UserService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@Controller
//@RequestMapping(path = "/mvc/user")
//public class UserControllerMvc {
//
//    private final UserService userService;
//    private final FullDayTimeOffService fullDayTimeOffService;
//    private final PartialDayTimeOffService partialDayTimeOffService;
//
//    public UserControllerMvc(UserService userService, FullDayTimeOffService fullDayTimeOffService, PartialDayTimeOffService partialDayTimeOffService) {
//        this.userService = userService;
//        this.fullDayTimeOffService = fullDayTimeOffService;
//        this.partialDayTimeOffService = partialDayTimeOffService;
//    }
//
//    @GetMapping("/all")
//    public String getUsers(Model model) {
//
//        model.addAttribute("users", userService.getAllUsers());
//
//        return "users";
//
//    }
//
//    @GetMapping("/{id}")
//    public String getUser(@PathVariable("id") String id, Model model) {
//
//        model.addAttribute("user", userService.getUser( Long.parseLong(id) ));
//
//        model.addAttribute("partialDayTimeOffs", partialDayTimeOffService.getPartialDayTimeoff( Long.parseLong(id) ));
//
//        model.addAttribute("fullDayTimeOffs", fullDayTimeOffService.getFullDayTimeoff(Long.parseLong(id)));
//
//        return "user";
//
//    }
//
//    @GetMapping("/partial-day-timeoff/get-form/{idUser}")
//    public String addPartialForm(@PathVariable("idUser") String userId, Model model) {
//        model.addAttribute("userId", userId);
//
//        return "partialDayForm";
//    }
//
//    @GetMapping("/partial-day-timeoff/delete/{id}/user/{userId}")
//    public String deletePartialTimeOff(@PathVariable("id") String idPartialTimeOff,
//                                       @PathVariable("userId") String userId) {
//
//        partialDayTimeOffService.deletePartialDayTimeOff( Long.parseLong(idPartialTimeOff));
//
//        return "redirect:/mvc/user/" + userId;
//    }
//
//
//    @PostMapping("/partial-day-timeoff/add/{userId}")
//    public String savePartialDayTimeOff(@PathVariable("userId") String userId,
//                                        String date,
//                                        String morningFrom,
//                                        String morningTo,
//                                        String afternoonFrom,
//                                        String afternoonTo) {
//
//        TimeRangeDTO timeRangeDTO1 = new TimeRangeDTO();
//        timeRangeDTO1.setFrom(morningFrom);
//        timeRangeDTO1.setTo(morningTo);
//        TimeRangeDTO timeRangeDTO2 = new TimeRangeDTO();
//        timeRangeDTO2.setFrom(afternoonFrom);
//        timeRangeDTO2.setTo(afternoonTo);
//
//        PartialDayTimeOffDTO partialDayTimeOffDTO = new PartialDayTimeOffDTO();
//        partialDayTimeOffDTO.setDate(date);
//        partialDayTimeOffDTO.setTimeRangeDTOList(List.of(timeRangeDTO1, timeRangeDTO2));
//
//        partialDayTimeOffService.save(partialDayTimeOffDTO, Long.parseLong(userId));
//
//        return "redirect:/mvc/user/" + userId;
//    }
//
//
//    @GetMapping("/full-day-timeoff/edit/{id}/user/{userId}")
//    public String getFullDayTimeOffForm(@PathVariable("userId") String userId,
//                                        @PathVariable("id") String id,
//                                        Model model
//                                        ) {
//
//        if (Long.parseLong(id) == 0) {
//            model.addAttribute("fullDayTimeOffDTO", new FullDayTimeOffDTO());
//        } else {
//            model.addAttribute("fullDayTimeOffDTO", fullDayTimeOffService.getFullDayTimeOffById(Long.parseLong(id)));
//        }
//
//        model.addAttribute("userId", userId);
//
//        return "fullDayForm";
//    }
//
//    @PostMapping("/full-day-timeoff/edit/{id}/user/{userId}")
//    public String editFullDayTimeOff(@PathVariable("userId") String userId,
//                                     @PathVariable("id") String id,
//                                     String from,
//                                     String to) {
//
//        FullDayTimeOffDTO fullDayTimeOffDTO = new FullDayTimeOffDTO();
//
//        if (Long.parseLong(id) != 0) {
//            fullDayTimeOffDTO.setId(Long.parseLong(id));
//        }
//
//        fullDayTimeOffDTO.setFrom(from);
//        fullDayTimeOffDTO.setTo(to);
//        fullDayTimeOffService.save(fullDayTimeOffDTO, Long.parseLong(userId));
//
//        return "redirect:/mvc/user/" + userId;
//    }
//
//    @GetMapping("/full-day-timeoff/delete/{id}/user/{userId}")
//    public String deleteFullTimeOff(@PathVariable("id") String idFullTimeOff,
//                                    @PathVariable("userId") String userId) {
//
//        fullDayTimeOffService.deleteFullDayTimeOff(Long.parseLong(idFullTimeOff));
//
//        return "redirect:/mvc/user/" + userId;
//
//    }
//
//    }
