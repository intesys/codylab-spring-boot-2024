package it.intesys.academy;

import it.intesys.academy.model.FullDayTimeOffRequest;

import java.time.LocalDate;
import java.time.Month;

public class TimeOffTracker {

    public static void main(String[] args) {

        FullDayTimeOffRequest rq = new FullDayTimeOffRequest(
                LocalDate.of(2024, Month.JULY, 9),
                LocalDate.of(2024, Month.AUGUST, 9)
        );
        System.out.println(rq);
        

    }
}
