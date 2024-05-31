package it.intesys.academy.repository;

import it.intesys.academy.model.FullDayTimeOffRequest;
import it.intesys.academy.model.PartialDayTimeOffRequest;
import it.intesys.academy.model.TimeOffRequest;
import it.intesys.academy.model.TimeRange;

import java.time.Duration;
import java.util.List;

public interface TimeOffRepository {

  List<TimeOffRequest> getAllTimeOffRequestsFor(Long userId);
  Duration getAvailableTimeOffForUser(Long userId);

  List<FullDayTimeOffRequest> getFullDayTimeOffByUserId(Long userId);
  List<PartialDayTimeOffRequest> getPartialDayTimeOffByUserId(Long userId);
  List<TimeRange> getTimeRangesByPartialDayId(Long partialDayId);
}
