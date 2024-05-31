package it.intesys.academy.service;

import it.intesys.academy.dto.FullDayTimeOffDTO;
import it.intesys.academy.dto.PartialDayTimeOffDTO;
import it.intesys.academy.dto.TimeOffBalance;
import it.intesys.academy.mapper.FullDayTimeOffModelMapper;
import it.intesys.academy.mapper.PartialDayTimeOffModelMapper;
import it.intesys.academy.model.TimeOffRequest;
import it.intesys.academy.repository.SQLTimeOffRepository;
import it.intesys.academy.repository.TimeOffRepository;
import it.intesys.academy.utils.StringUtils;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TimeOffService {

  private final TimeOffRepository timeOffRepository;
  private final SQLTimeOffRepository sqlTimeOffRepository;

  public TimeOffService(@Qualifier("SQLTimeOffRepository") TimeOffRepository timeOffRepository, SQLTimeOffRepository sqlTimeOffRepository) {
    this.timeOffRepository = timeOffRepository;
    this.sqlTimeOffRepository = sqlTimeOffRepository;
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
            .map(TimeOffRequest::getDuration)
            .reduce(Duration.ZERO, Duration::plus);
  }

  public List<FullDayTimeOffDTO> getFullDayTimeOffByUserId(Long userId) {
    return sqlTimeOffRepository.getFullDayTimeOffByUserId(userId).stream().map(FullDayTimeOffModelMapper::fromEntityToDTO).collect(Collectors.toList());
  }

  public List<PartialDayTimeOffDTO> getPartialDayTimeOffByUserId(Long userId) {
    return sqlTimeOffRepository.getPartialDayTimeOffByUserId(userId).stream().map(PartialDayTimeOffModelMapper::fromEntityToDTO).collect(Collectors.toList());
  }


}
