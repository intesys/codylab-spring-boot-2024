package it.intesys.academy.repository;

import it.intesys.academy.model.TimeOffRequest;
import java.time.Duration;
import java.util.List;

public interface TimeOffRepository {

  List<TimeOffRequest> getAllTimeOffRequestsFor(Long userId);
  Duration getAvailableTimeOffForUser(Long userId);



}
