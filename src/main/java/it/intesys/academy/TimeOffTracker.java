package it.intesys.academy;

import it.intesys.academy.model.FullDayTimeOffRequest;
import it.intesys.academy.model.PartialDayTimeOffRequest;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeOffTracker {

    public static void main(String[] args) {

        FullDayTimeOffRequest fullDayRequest = new FullDayTimeOffRequest(LocalDate.of(2024, 6, 8), LocalDate.of(2024, 6, 18));
        System.out.println(fullDayRequest);

        PartialDayTimeOffRequest partialDayRequest = new PartialDayTimeOffRequest(LocalDate.of(2024, 6, 8), LocalTime.of(8, 20, 45), LocalTime.of(8, 20, 45));
        System.out.println(partialDayRequest);

    }
}
