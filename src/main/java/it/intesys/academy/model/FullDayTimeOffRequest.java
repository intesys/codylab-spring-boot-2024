package it.intesys.academy.model;

import java.time.LocalDate;

public class FullDayTimeOffRequest {
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
        if(startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
    }

    public String toString() {
        return "FullDayTimeOffRequest{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
