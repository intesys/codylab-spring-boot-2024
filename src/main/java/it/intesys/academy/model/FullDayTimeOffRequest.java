package it.intesys.academy.model;

import it.intesys.academy.utils.StringUtils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class FullDayTimeOffRequest implements TimeOffRequest {
    protected LocalDate startDate;
    protected LocalDate endDate;

    public FullDayTimeOffRequest(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        validateDates();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void validateDates() {
        Objects.requireNonNull(startDate, "From date cannot be null");
        Objects.requireNonNull(endDate, "End date cannot be null");

        if(startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
    }

    public Duration getDuration() {
        long daysOff = ChronoUnit.DAYS.between(startDate, endDate);
        return Duration.ofHours(8).multipliedBy(daysOff + 1);
    }

    public String toString() {
        return "Full Day Time Off (" + startDate + " - " + endDate + ") / " + StringUtils.format(getDuration());
    }
}
