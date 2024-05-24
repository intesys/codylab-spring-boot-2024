package it.intesys.academy.controller;

import io.javalin.http.Context;
import io.javalin.validation.Validator;
import it.intesys.academy.dto.TimeOffBalanceDTO;
import it.intesys.academy.service.TimeOffService;
import org.springframework.stereotype.Controller;

@Controller
public class TimeOffController {
    private final TimeOffService timeOffService;

    public TimeOffController(TimeOffService timeOffService) {
        this.timeOffService = timeOffService;
    }

    public void getTimeOffRequest(Context httpContext) {
        Validator<Long> userId = httpContext.queryParamAsClass("userId", Long.class);
        if (!userId.hasValue()) {
            throw new IllegalArgumentException("Invalid user id");
        }

        TimeOffBalanceDTO timeOffBalance = timeOffService.getTimeOffBalance(userId.get());

        httpContext.json(timeOffBalance);
    }
}
