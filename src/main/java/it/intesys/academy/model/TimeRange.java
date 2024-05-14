package it.intesys.academy.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class TimeRange {

  private LocalTime start;
  private LocalTime end;

  public TimeRange(LocalTime start, LocalTime end) {
    this.start = start;
    this.end = end;
    validate();
  }

  private void validate() {
    Objects.requireNonNull(start, "From time cannot be null");
    Objects.requireNonNull(end, "End time cannot be null");
    if (start.isAfter(end)) {
      throw new IllegalArgumentException("From time cannot be after end time");
    }
  }

  public LocalTime getFrom() {
    return start;
  }

  public void setFrom(LocalTime from) {
    this.start = from;
  }

  public LocalTime getTo() {
    return end;
  }

  public void setTo(LocalTime to) {
    this.end = to;
  }

  public Duration duration() {
    return Duration.ofMinutes(ChronoUnit.MINUTES.between(start, end));
  }
}
