package it.intesys.academy.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PartialDayTimeOffRequest {
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

    @Override
    public String toString() {
        return "PartialDayTimeOffRequest{" +
                "date=" + date +
                ", timeRanges=" + timeRanges +
                '}';
    }
}
