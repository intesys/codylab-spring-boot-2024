package it.intesys.academy.model;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PartialDayTimeOffRequest implements TimeOffRequest {
    protected LocalDate date;
    private List<TimeRange> timeRanges;

    public PartialDayTimeOffRequest(LocalDate date, List<TimeRange> timeRanges) {
        this.date = date;
        this.timeRanges = timeRanges;
        validate();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<TimeRange> getTimeRanges() {
        return timeRanges;
    }

    public void setTimeRanges(List<TimeRange> timeRanges) {
        this.timeRanges = timeRanges;
    }

    private void validate() {
        if (timeRanges.isEmpty()) {
            throw new IllegalArgumentException("Time ranges list cannot be empty");
        }
    }

    public Duration getDuration() {
        Duration duration = Duration.ZERO;
        for (TimeRange timeRange : timeRanges) {
            duration = duration.plus(timeRange.duration());
        }
        return duration;
    }

    @Override
    public String toString() {
        var timeRangeString = timeRanges.stream()
            .map(tr->tr.getFrom() + " - " + tr.getTo())
            .collect(Collectors.joining(","));
        return "Partial Day Time Off (" + date + " [" + timeRangeString + "]) / " + getDuration().toHours() + "h " + getDuration().toMinutesPart() + "m";
    }
}
