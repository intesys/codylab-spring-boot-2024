package it.intesys.academy.service;

import it.intesys.academy.dto.TimeOffBalanceDTO;
import it.intesys.academy.model.TimeOffRequest;
import it.intesys.academy.repository.TimeOffRepository;
import it.intesys.academy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class TimeOffService {
    private final TimeOffRepository timeOffRepository;

    public TimeOffService(@Qualifier("SQLTimeOffRepository") TimeOffRepository timeOffRepository) {
        this.timeOffRepository = timeOffRepository;
    }

    public Duration getTimeOffDurationForUser(Long userId) {
        List<TimeOffRequest> timeOffRequestsForUser = timeOffRepository.getAllTimeOffRequestsFor(userId);
        for (TimeOffRequest timeOffRequest : timeOffRequestsForUser) {
            System.out.println(timeOffRequest);
        }

        return timeOffRequestsForUser.stream()
                .map(TimeOffRequest::getDuration)
                .reduce(Duration.ZERO, Duration::plus);
    }

    public TimeOffBalanceDTO getTimeOffBalance(Long userId) {
        Duration availableTimeOff = timeOffRepository.getAvailableTimeOffForUser(userId);
        Duration takenTimeOff = getTimeOffDurationForUser(userId);

        return new TimeOffBalanceDTO(
            StringUtils.format(availableTimeOff),
            StringUtils.format(takenTimeOff),
            StringUtils.format(availableTimeOff.minus(takenTimeOff))
        );
    }

}
