package it.intesys.academy.repository;

import it.intesys.academy.model.TimeOffRequest;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.List;

@Repository
public interface TimeOffRepository {

    List<TimeOffRequest> getAllTimeOffRequestsFor(Long userId);
    Duration getAvailableTimeOffForUser(Long userId);

}
