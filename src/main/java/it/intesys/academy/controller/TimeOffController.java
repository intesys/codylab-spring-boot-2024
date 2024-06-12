package it.intesys.academy.controller;

import it.intesys.academy.service.PartialDayTimeOffService;
import it.intesys.academy.service.FullDayTimeOffService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeOffController {

  private final PartialDayTimeOffService timeOffService;
  private final FullDayTimeOffService fullDayTimeOffService;

  public TimeOffController(PartialDayTimeOffService timeOffService, FullDayTimeOffService fullDayTimeOffService) {
    this.timeOffService = timeOffService;
    this.fullDayTimeOffService = fullDayTimeOffService;
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
