package it.intesys.academy;

import io.javalin.Javalin;
import it.intesys.academy.controller.TimeOffController;
import it.intesys.academy.repository.DummyTimeOffRequestRepository;
import it.intesys.academy.repository.TimeOffRepository;
import it.intesys.academy.service.TimeOffService;
import it.intesys.academy.utils.StringUtils;

import java.time.Duration;

public class TimeOffTracker {

    public static void main(String[] args) {

        TimeOffRepository timeOffRepository = new DummyTimeOffRequestRepository();
        TimeOffService timeOffService = new TimeOffService(timeOffRepository);
        TimeOffController timeOffController = new TimeOffController(timeOffService);

        var app = Javalin.create(/*config*/)
            .get("/balance", timeOffController::getTimeOffRequest)
            .start(8080);

//        long userId = 10L;
//
//        Duration timeOffDurationForUser = timeOffService.getTimeOffDurationForUser(userId);
//        System.out.println("User " + userId + " has requested " + StringUtils.format(timeOffDurationForUser) + " time off");
    }
}
