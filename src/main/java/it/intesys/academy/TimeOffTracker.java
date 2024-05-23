package it.intesys.academy;

import io.javalin.Javalin;
import it.intesys.academy.config.AppConfiguration;
import it.intesys.academy.controller.TimeOffController;
import java.util.Properties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public class TimeOffTracker {

    public static void main(String[] args) {

        ApplicationContext springContext = new AnnotationConfigApplicationContext(AppConfiguration.class);

        Environment environment = springContext.getEnvironment();
        TimeOffController timeOffController = springContext.getBean(TimeOffController.class);

        var app = Javalin.create(/*config*/)
            .get("/balance", timeOffController::getTimeOffRequests)
            .start(environment.getProperty("app.port", Integer.class));
    }


}
