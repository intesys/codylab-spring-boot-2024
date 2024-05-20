package it.intesys.academy.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import it.intesys.academy.utils.StringUtils;

public class FullDayTimeOffRequest implements TimeOffRequest {
    private LocalDate start;
    private LocalDate end;

    public FullDayTimeOffRequest(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
        validate();
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    private void validate() {
        Objects.requireNonNull(start, "Start Date cannot be null");
        Objects.requireNonNull(end, "End Date cannot be null");

        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start date can't be after end date");
        }
    }

    @Override
    public Duration getDuration() {
        long daysBetween = ChronoUnit.DAYS.between(start, end);
        System.out.println(daysBetween);
        return Duration.ofHours(8).multipliedBy(daysBetween + 1);
    }

    @Override
    public String toString() {
        return String.format("Full Day Time Off [Start: %s, End: %s] | Time Off: %s",
                start, end, StringUtils.formatTime(getDuration()));
    }
}
