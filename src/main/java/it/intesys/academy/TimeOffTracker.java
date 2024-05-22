package it.intesys.academy;

import it.intesys.academy.model.FullDayTimeOffRequest;
import it.intesys.academy.model.PartialDayTimeOffRequest;
import it.intesys.academy.model.TimeInterval;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

public class TimeOffTracker {

    public static void main(String[] args) {

        FullDayTimeOffRequest rq = new FullDayTimeOffRequest(
                LocalDate.of(2024, Month.JULY, 9),
                LocalDate.of(2024, Month.AUGUST, 9)
        );
        System.out.println(rq);

        PartialDayTimeOffRequest prq = new PartialDayTimeOffRequest(
                LocalDate.parse("2024-06-01"),
                List.of(new TimeInterval(LocalTime.parse("12:00"), LocalTime.parse("12:30")))
        );
        System.out.println(prq);
        

    }
}
