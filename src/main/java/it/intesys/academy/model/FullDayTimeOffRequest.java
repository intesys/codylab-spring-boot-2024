package it.intesys.academy.model;

import it.intesys.academy.utils.StringUtils;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class FullDayTimeOffRequest extends AnnotatedTimeOffRequest {

  private LocalDate start;
  private LocalDate end;

  public FullDayTimeOffRequest(LocalDate start, LocalDate end) {
    this.start = start;
    this.end = end;
    validate();
  }

  private void validate() {
    Objects.requireNonNull(start, "From date cannot be null");
    Objects.requireNonNull(end, "End date cannot be null");

    if (start.isAfter(end)) {
      throw new IllegalArgumentException("From time cannot be after end time");
    }
  }

  public LocalDate getFrom() {
    return start;
  }

  public void setFrom(LocalDate from) {
    this.start = from;
  }

  public LocalDate getTo() {
    return end;
  }

  public void setTo(LocalDate to) {
    this.end = to;
  }

  @Override
  public Duration getDuration() {
    long daysBetween = ChronoUnit.DAYS.between(start, end);
    return Duration.ofHours(8).multipliedBy(daysBetween + 1);
  }

  @Override
  public String toString() {
    return "Full Day Time Off (" + start + " - " + end + ") / " + StringUtils.format(getDuration());
  }
}
