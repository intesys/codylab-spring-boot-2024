package it.intesys.academy;

import io.javalin.Javalin;
import it.intesys.academy.config.AppConfiguration;
import it.intesys.academy.controller.TimeOffController;

public class TimeOffTracker {

    public static void main(String[] args) {

        TimeOffController timeOffController = AppConfiguration.timeOffController();

        var app = Javalin.create(/*config*/)
            .get("/balance", timeOffController::getTimeOffRequests)
            .start(Integer.parseInt(AppConfiguration.appProperties().getProperty("app.port")));
    }


}
