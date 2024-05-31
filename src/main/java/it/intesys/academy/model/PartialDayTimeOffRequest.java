package it.intesys.academy.model;

import it.intesys.academy.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
public class PartialDayTimeOffRequest extends AnnotatedTimeOffRequest {

  private long id;
  private LocalDate timeOffDay;
  private List<TimeRange> timeRanges;
  private long userId;

  public PartialDayTimeOffRequest(LocalDate timeOffDay, List<TimeRange> timeRanges) {
    this.timeOffDay = timeOffDay;
    this.timeRanges = timeRanges;
    validate();
  }
  public PartialDayTimeOffRequest() {
  }

  private void validate() {
    Objects.requireNonNull(timeOffDay, "Time off day cannot be null");
    Objects.requireNonNull(timeRanges, "Time ranges list cannot be null");

    if (timeRanges.isEmpty()) {
      throw new IllegalArgumentException("Time ranges list cannot be empty");
    }
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
        .map(tr->tr.getStart() + " - " + tr.getEnd())
        .collect(Collectors.joining(","));
    return "Partial Day Time Off (" + timeOffDay + " [" + timeRangeString + "]) / " + StringUtils.format(getDuration());
  }
}
