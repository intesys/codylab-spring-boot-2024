package it.intesys.academy.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Test;

class PartialDayTimeOffRequestTest {

  @Test
  void shouldCalculateDurationWithOneInterval() {
    var partialDayTimeOffRequest = new PartialDayTimeOffRequest(
        LocalDate.parse("2024-06-01"),
        List.of(new TimeRange(LocalTime.parse("12:00"), LocalTime.parse("12:30")))
    );

    assertEquals(partialDayTimeOffRequest.getDuration(), Duration.ofMinutes(30));
  }

  @Test
  void shouldCalculateDurationWithTwoIntervals() {
    var partialDayTimeOffRequest = new PartialDayTimeOffRequest(
        LocalDate.parse("2024-06-01"),
        List.of(
            new TimeRange(LocalTime.parse("12:00"), LocalTime.parse("13:00")),
            new TimeRange(LocalTime.parse("14:00"), LocalTime.parse("14:30"))
        )
    );

    assertEquals(partialDayTimeOffRequest.getDuration(), Duration.ofMinutes(90));
  }
}
