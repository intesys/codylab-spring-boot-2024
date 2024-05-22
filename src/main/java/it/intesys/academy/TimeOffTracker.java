package it.intesys.academy;

import it.intesys.academy.model.FullDayTimeOffRequest;
import it.intesys.academy.model.PartialDayTimeOffRequest;
import it.intesys.academy.model.TimeOffRequest;
import it.intesys.academy.model.TimeRange;
import it.intesys.academy.repository.TimeOffRequestRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimeOffTracker {

    public static void main(String[] args) {

//        TimeOffRequestRepository timeOffRepository = new TimeOffRequestRepository();
        Long userId = 11L;
        List<TimeOffRequest> fullDayRequest = new TimeOffRequestRepository().timeOffRequests(userId);
        Duration holidayDuration = Duration.ZERO;
        for ( TimeOffRequest request: fullDayRequest) {
            holidayDuration = holidayDuration.plus(request.getDuration());
            System.out.println(request);
        }
        System.out.println("User " + userId + " has requested " + holidayDuration.toHours() + "h " + holidayDuration.toMinutesPart() + "m time off");

    }
}
