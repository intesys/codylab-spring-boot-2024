package it.intesys.academy.model;

import lombok.Data;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Data
public class TimeRange {

  private Long id;
  private LocalTime start;
  private LocalTime end;
  private Long PartialDayId;

  public TimeRange(LocalTime start, LocalTime end) {
    this.start = start;
    this.end = end;
    validate();
  }

  public TimeRange() {
  }

  private void validate() {
    Objects.requireNonNull(start, "From time cannot be null");
    Objects.requireNonNull(end, "End time cannot be null");
    if (start.isAfter(end)) {
      throw new IllegalArgumentException("From time cannot be after end time");
    }
  }

  public Duration duration() {
    return Duration.ofMinutes(ChronoUnit.MINUTES.between(start, end));
  }
}
