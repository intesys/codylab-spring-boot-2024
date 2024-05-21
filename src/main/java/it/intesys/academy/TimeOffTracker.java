package it.intesys.academy;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import it.intesys.academy.controller.TimeOffController;
import it.intesys.academy.repository.DummyTimeOffRequestRepository;
import it.intesys.academy.repository.TimeOffRepository;
import it.intesys.academy.service.TimeOffService;
import org.jetbrains.annotations.NotNull;

public class TimeOffTracker {

    public static void main(String[] args) {

        TimeOffRepository timeOffRepository = new DummyTimeOffRequestRepository();
        TimeOffService timeOffService = new TimeOffService(timeOffRepository);
        TimeOffController timeOffController = new TimeOffController(timeOffService);


        var app = Javalin.create(/*config*/)
            .get("/balance", timeOffController::getTimeOffRequests)
            .start(7070);
    }


}
