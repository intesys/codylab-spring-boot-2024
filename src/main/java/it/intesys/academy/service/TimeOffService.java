package it.intesys.academy.service;

import it.intesys.academy.dto.TimeOffBalance;
import it.intesys.academy.model.TimeOffRequest;
import it.intesys.academy.repository.TimeOffRepository;
import it.intesys.academy.utils.StringUtils;
import java.time.Duration;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TimeOffService {

  private final TimeOffRepository timeOffRepository;

  public TimeOffService(@Qualifier("SQLTimeOffRepository") TimeOffRepository timeOffRepository) {
    this.timeOffRepository = timeOffRepository;
  }
  
  public TimeOffBalance getTimeOffBalance(Long userId) {
    Duration takenTimeOff = getTimeOffDurationForUser(userId);
    Duration availableTimeOffForUser = timeOffRepository.getAvailableTimeOffForUser(userId);

    return new TimeOffBalance(
        StringUtils.format(takenTimeOff),
        StringUtils.format(availableTimeOffForUser),
        StringUtils.format(availableTimeOffForUser.minus(takenTimeOff))
    );

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
