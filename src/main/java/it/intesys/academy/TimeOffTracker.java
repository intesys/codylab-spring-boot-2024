package it.intesys.academy;

import io.javalin.Javalin;
import it.intesys.academy.config.AppConfiguration;
import it.intesys.academy.controller.TimeOffController;
import java.util.Properties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TimeOffTracker {

    public static void main(String[] args) {

        ApplicationContext springContext = new AnnotationConfigApplicationContext(AppConfiguration.class);

        TimeOffController timeOffController = springContext.getBean(TimeOffController.class);
        Properties props = springContext.getBean("appProperties", Properties.class);

        var app = Javalin.create(/*config*/)
            .get("/balance", timeOffController::getTimeOffRequests)
            .start(Integer.parseInt(props.getProperty("app.port")));
    }


}
