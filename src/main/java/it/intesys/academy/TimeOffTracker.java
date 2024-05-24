package it.intesys.academy;

import io.javalin.Javalin;
import it.intesys.academy.config.AppConfiguration;
import it.intesys.academy.controller.TimeOffController;
import it.intesys.academy.repository.SQLTimeOffRepository;
import it.intesys.academy.repository.TimeOffRepository;
import it.intesys.academy.service.TimeOffService;

public class TimeOffTracker {

    public static void main(String[] args) {

        TimeOffRepository timeOffRepository = new SQLTimeOffRepository(AppConfiguration.jdbcTemplate());
        TimeOffService timeOffService = new TimeOffService(timeOffRepository);
        TimeOffController timeOffController = new TimeOffController(timeOffService);

        var app = Javalin.create(/*config*/)
            .get("/balance", timeOffController::getTimeOffRequest)
            .start(Integer.parseInt(AppConfiguration.appProperties().getProperty("app.port")));

    }
}
