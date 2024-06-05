package it.intesys.academy.controller;

import it.intesys.academy.dto.TimeOffBalanceDTO;
import it.intesys.academy.service.TimeOffService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeOffController {

  private final TimeOffService timeOffService;

  public TimeOffController(TimeOffService timeOffService) {
    this.timeOffService = timeOffService;
  }

  @GetMapping("/balance")
  public TimeOffBalanceDTO getTimeOffRequests(@RequestParam("userId") Long userId) {
    if (userId == null) {
      throw new IllegalArgumentException("Missing user id");
    }
    return timeOffService.getTimeOffBalance(userId);
  }


}
