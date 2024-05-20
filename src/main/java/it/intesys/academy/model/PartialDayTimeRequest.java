package it.intesys.academy.model;

import java.time.Duration;

public class PartialDayTimeRequest implements TimeOffRequest {


    @Override
    public Duration getDuration() {
        return null;
    }
}
