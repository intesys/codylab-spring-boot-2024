package it.intesys.academy.repository;

import it.intesys.academy.model.FullDayTimeOffRequest;
import it.intesys.academy.model.PartialDayTimeOffRequest;
import it.intesys.academy.model.TimeOffRequest;
import it.intesys.academy.model.TimeRange;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DummyTimeOffRequestRepository implements TimeOffRepository {

  private final Map<Long, List<TimeOffRequest>> timeOffRequests;

  public DummyTimeOffRequestRepository() {
    this.timeOffRequests = init();
  }

  @Override
  public List<TimeOffRequest> getAllTimeOffRequestsFor(Long userId) {
    return timeOffRequests.getOrDefault(userId, List.of());
  }

  @Override
  public Duration getAvailableTimeOffForUser(Long userId) {
    return Duration.ofDays(20);
  }

  private Map<Long, List<TimeOffRequest>> init() {
    List<TimeOffRequest> timeOffRequests = new ArrayList<>();

    timeOffRequests.add(
        new FullDayTimeOffRequest(
            LocalDate.of(2024, Month.JULY, 1),
            LocalDate.of(2024, Month.JULY, 1)
        )
    );

    timeOffRequests.add(
        new FullDayTimeOffRequest(
            LocalDate.of(2024, Month.AUGUST, 12),
            LocalDate.of(2024, Month.AUGUST, 16)
        )
    );

    timeOffRequests.add(
        new PartialDayTimeOffRequest(
            LocalDate.of(2024, Month.JUNE, 15),
            List.of(new TimeRange(LocalTime.of(12, 00), LocalTime.of(12, 30))
            )
        )
    );

    return Map.of(10L, timeOffRequests);

  }
}
