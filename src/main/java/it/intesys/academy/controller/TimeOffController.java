package it.intesys.academy.controller;

import io.javalin.http.Context;
import io.javalin.validation.Validator;
import it.intesys.academy.dto.TimeOffBalance;
import it.intesys.academy.service.TimeOffService;

public class TimeOffController {

  private final TimeOffService timeOffService;

  public TimeOffController(TimeOffService timeOffService) {
    this.timeOffService = timeOffService;
  }

  public void getTimeOffRequests(Context httpContext) {
    Validator<Long> userId = httpContext.queryParamAsClass("userId", Long.class);
    if (!userId.hasValue()) {
      throw new IllegalArgumentException("Missing user id");
    }

    TimeOffBalance timeOffBalance = timeOffService.getTimeOffBalance(userId.get());

    httpContext.json(timeOffBalance);
  }


}
