package it.intesys.academy.model;

import it.intesys.academy.utils.StringUtils;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PartialDayTimeOffRequest implements TimeOffRequest {
    private LocalDate timeOffDay;
    private List<TimeInterval> timeOffIntervals;

    public PartialDayTimeOffRequest(LocalDate timeOffDay, List<TimeInterval> tOffInter) {
        this.timeOffDay = timeOffDay;
        this.timeOffIntervals = tOffInter;
        validate();
    }

    private void validate() {
        Objects.requireNonNull(timeOffDay, "Time Off Day cannot be null");
        Objects.requireNonNull(timeOffIntervals, "Time Off Intervals cannot be null");

        if (timeOffIntervals.isEmpty()) {
            throw new IllegalArgumentException("Start date can't be after end date");
        }
    }

    @Override
    public Duration getDuration() {
        Duration duration = Duration.ZERO;

        for (TimeInterval timeOffInterval : timeOffIntervals) {
            duration = duration.plus(timeOffInterval.duration());
        }
        return duration;
    }

    @Override
    public String toString() {
        var timeIntervalsStr = timeOffIntervals.stream()
                .map(tr->tr.getStart() + " - " + tr.getEnd())
                .collect(Collectors.joining(","));

        return String.format("Partial Day Time Off [%s] [%s] | Time Off: %s",
                timeOffDay, timeIntervalsStr, StringUtils.formatTime(getDuration()));
    }
}
