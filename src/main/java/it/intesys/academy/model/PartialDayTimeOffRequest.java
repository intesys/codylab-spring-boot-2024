package it.intesys.academy.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class PartialDayTimeOffRequest {
    protected LocalDate date;
    protected LocalTime start;
    protected LocalTime end;

    public PartialDayTimeOffRequest(LocalDate date, LocalTime start, LocalTime end) {
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "PartialDayTimeOffRequest{" +
                "date=" + date +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
