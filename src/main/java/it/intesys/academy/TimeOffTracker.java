package it.intesys.academy;

import it.intesys.academy.model.FullDayTimeOffRequest;
import it.intesys.academy.model.PartialDayTimeOffRequest;
import it.intesys.academy.model.TimeRange;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimeOffTracker {

    public static void main(String[] args) {

        // fullDayRequest
        FullDayTimeOffRequest fullDayRequest = new FullDayTimeOffRequest(
                LocalDate.of(2024, 6, 8),
                LocalDate.of(2024, 6, 18)
        );
        System.out.println(fullDayRequest);

        // partialDayRequest
        List<TimeRange> times = new ArrayList<>(List.of(
                new TimeRange(LocalTime.of(8, 0, 0), LocalTime.of(10, 0, 0)),
                new TimeRange(LocalTime.of(17, 0, 0), LocalTime.of(18, 0, 0))
        ));;
        PartialDayTimeOffRequest partialDayRequest = new PartialDayTimeOffRequest(LocalDate.of(2024, 6, 8), times);
        System.out.println(partialDayRequest);

    }
}
