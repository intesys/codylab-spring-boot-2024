package it.intesys.academy.model;

import it.intesys.academy.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
public class FullDayTimeOffRequest extends AnnotatedTimeOffRequest {

  private long id;
  private LocalDate start;
  private LocalDate end;
  private long userId;

  public FullDayTimeOffRequest(LocalDate start, LocalDate end) {
    this.start = start;
    this.end = end;
    validate();
  }

  public FullDayTimeOffRequest() {
  }

  private void validate() {
    Objects.requireNonNull(start, "From date cannot be null");
    Objects.requireNonNull(end, "End date cannot be null");

    if (start.isAfter(end)) {
      throw new IllegalArgumentException("From time cannot be after end time");
    }
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
