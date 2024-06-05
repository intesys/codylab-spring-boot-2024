package it.intesys.academy.service;

import it.intesys.academy.dto.FullDayTimeOffDTO;
import it.intesys.academy.dto.PartialDayTimeOffDTO;
import it.intesys.academy.dto.TimeOffBalanceDTO;
import it.intesys.academy.dto.TimeRangeDTO;
import it.intesys.academy.mapper.dto.FullDayTimeOffModelMapper;
import it.intesys.academy.mapper.dto.PartialDayTimeOffModelMapper;
import it.intesys.academy.mapper.dto.TimeRangeModelMapper;
import it.intesys.academy.model.TimeOffRequest;
import it.intesys.academy.model.TimeRange;
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

  public TimeOffBalanceDTO getTimeOffBalance(Long userId) {
    Duration takenTimeOff = getTimeOffDurationForUser(userId);
    Duration availableTimeOffForUser = timeOffRepository.getAvailableTimeOffForUser(userId);

    return new TimeOffBalanceDTO(
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
    List<PartialDayTimeOffDTO> partialDayTimeOffByUserId = sqlTimeOffRepository.getPartialDayTimeOffByUserId(userId).stream().map(PartialDayTimeOffModelMapper::fromEntityToDTO).collect(Collectors.toList());

    for (PartialDayTimeOffDTO partialDayTimeOffDTO : partialDayTimeOffByUserId) {
      Long partialDayId = partialDayTimeOffDTO.getId();
      List<TimeRangeDTO> timeRanges = sqlTimeOffRepository.getTimeRangesByPartialDayId(partialDayId).stream().map(TimeRangeModelMapper::fromEntityToDTO).collect(Collectors.toList());
      partialDayTimeOffDTO.setTimeRanges(timeRanges);
    }

    return partialDayTimeOffByUserId;
  }

  public void deletePartialDayTimeOffTimeRange(Long id) {
    Long partialDayTimeOffId = sqlTimeOffRepository.getPartialDayTimeOffIdByTimeRangeId(id)
            .stream()
            .map(TimeRange::getPartialDayId)
            .findFirst()
            .orElse(null);

    sqlTimeOffRepository.deleteTimeRange(id);

    if (sqlTimeOffRepository.getTimeRangesByPartialDayId(partialDayTimeOffId).isEmpty()) {
      sqlTimeOffRepository.deletePartialDayTimeOff(partialDayTimeOffId);
    }
  }
  public void deleteFullDayTimeOff(Long id) {

    sqlTimeOffRepository.deleteFullDayTimeOff(id);

  }

}
