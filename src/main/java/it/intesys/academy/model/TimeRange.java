package it.intesys.academy.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeRange {
    private LocalTime startTime;
    private LocalTime endTime;

    public TimeRange(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        validate();
    }

    private void validate() {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("From time cannot be after endTime time");
        }
    }

    public LocalTime getFrom() {
        return startTime;
    }

    public void setFrom(LocalTime from) {
        this.startTime = from;
    }

    public LocalTime getTo() {
        return endTime;
    }

    public void setTo(LocalTime to) {
        this.endTime = to;
    }

    public Duration duration() {
        return Duration.ofMinutes(ChronoUnit.MINUTES.between(startTime, endTime));
    }

    public String toString() {
        return "TimeRange{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
