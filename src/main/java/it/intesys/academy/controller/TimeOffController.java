package it.intesys.academy.controller;

import it.intesys.academy.service.PartialDayTimeOffService;
import org.springframework.web.bind.annotation.RestController;

//@RestController restituisce una vista
@RestController
public class TimeOffController {

  private final PartialDayTimeOffService timeOffService;

  public TimeOffController(PartialDayTimeOffService timeOffService) {
    this.timeOffService = timeOffService;
  }

  /*
  @GetMapping("/balance")
  public TimeOffBalance getTimeOffRequests(@RequestParam("userId") Long userId) {
    if (userId == null) {
      throw new IllegalArgumentException("Missing user id");
    }
    return timeOffService.getTimeOffBalance(userId);
  }
   */

}
