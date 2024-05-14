package it.intesys.academy.service;

import it.intesys.academy.model.TimeOffRequest;
import it.intesys.academy.repository.TimeOffRepository;
import java.time.Duration;
import java.util.List;

public class TimeOffService {

  private final TimeOffRepository timeOffRepository;

  public TimeOffService(TimeOffRepository timeOffRepository) {
    this.timeOffRepository = timeOffRepository;
  }

  public Duration getTimeOffDurationForUser(Long userId) {
    List<TimeOffRequest> timeOffRequestsForUser = timeOffRepository.getAllTimeOffRequestsFor(userId);

    for (TimeOffRequest timeOffRequest : timeOffRequestsForUser) {
      System.out.println(timeOffRequest.toString());
    }

    return timeOffRequestsForUser.stream()
        .map(timeOffRequest -> timeOffRequest.getDuration())
        .reduce(Duration.ZERO, (duration, duration2) -> duration.plus(duration2));
  }

}
