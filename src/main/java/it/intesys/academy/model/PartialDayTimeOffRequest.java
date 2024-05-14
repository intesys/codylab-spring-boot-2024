package it.intesys.academy.model;

import it.intesys.academy.utils.StringUtils;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PartialDayTimeOffRequest extends AnnotatedTimeOffRequest {

  private LocalDate timeOffDay;
  private List<TimeRange> timeRanges;

  public PartialDayTimeOffRequest(LocalDate timeOffDay, List<TimeRange> timeRanges) {
    this.timeOffDay = timeOffDay;
    this.timeRanges = timeRanges;
    validate();
  }

  private void validate() {
    Objects.requireNonNull(timeOffDay, "Time off day cannot be null");
    Objects.requireNonNull(timeRanges, "Time ranges list cannot be null");

    if (timeRanges.isEmpty()) {
      throw new IllegalArgumentException("Time ranges list cannot be empty");
    }
  }

  public LocalDate getLocalDate() {
    return timeOffDay;
  }

  public void setLocalDate(LocalDate localDate) {
    this.timeOffDay = localDate;
  }

  public List<TimeRange> getTimeRanges() {
    return timeRanges;
  }

  public void setTimeRanges(List<TimeRange> timeRanges) {
    this.timeRanges = timeRanges;
  }

  @Override
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
    return "Partial Day Time Off (" + timeOffDay + " [" + timeRangeString + "]) / " + StringUtils.format(getDuration());
  }
}
