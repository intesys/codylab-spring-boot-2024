package it.intesys.academy;

import it.intesys.academy.repository.DummyTimeOffRequestRepository;
import it.intesys.academy.repository.TimeOffRepository;
import it.intesys.academy.service.TimeOffService;
import it.intesys.academy.utils.StringUtils;
import java.time.Duration;

public class TimeOffTracker {

    public static void main(String[] args) {

        TimeOffRepository timeOffRepository = new DummyTimeOffRequestRepository();
        TimeOffService timeOffService = new TimeOffService(timeOffRepository);
        long userId = 10L;

        Duration timeOffDurationForUser = timeOffService.getTimeOffDurationForUser(userId);
        System.out.println("User " + userId + " has requested " + StringUtils.format(timeOffDurationForUser) + " time off");
    }


}
