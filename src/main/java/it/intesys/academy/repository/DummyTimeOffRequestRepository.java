package it.intesys.academy.repository;

import it.intesys.academy.model.FullDayTimeOffRequest;
import it.intesys.academy.model.PartialDayTimeOffRequest;
import it.intesys.academy.model.TimeOffRequest;
import it.intesys.academy.model.TimeRange;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DummyTimeOffRequestRepository implements TimeOffRepository {

    private final Map<Long, List<TimeOffRequest>> timeOffRequests;

    public DummyTimeOffRequestRepository() {
        this.timeOffRequests = init();
    }

    public List<TimeOffRequest> getAllTimeOffRequestsFor(Long userId) {
        return timeOffRequests.get(userId);
    }

    @Override
    public Duration getAvailableTimeOffForUser(Long userId) {
        return Duration.ofDays(20);
    }

    public Map<Long, List<TimeOffRequest>> init() {
        List<TimeOffRequest> timeOffRequests = new ArrayList<>();
        List<TimeRange> times = new ArrayList<>(List.of(
                new TimeRange(LocalTime.of(8, 0, 0), LocalTime.of(10, 0, 0)),
                new TimeRange(LocalTime.of(17, 0, 0), LocalTime.of(18, 0, 0))
        ));

        timeOffRequests.add(
                new FullDayTimeOffRequest(LocalDate.of(2024, 6, 8), LocalDate.of(2024, 6, 12))
        );
        timeOffRequests.add(
                new FullDayTimeOffRequest(LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 2))
        );
        timeOffRequests.add(
                new PartialDayTimeOffRequest(LocalDate.of(2024, 6, 8), times)
        );

        return Map.of(10L, timeOffRequests);
    }

}
