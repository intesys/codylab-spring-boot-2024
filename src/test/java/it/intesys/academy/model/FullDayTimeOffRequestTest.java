package it.intesys.academy.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class FullDayTimeOffRequestTest {

  @Test
  void shouldCalculate1DayDuration() {
    var timeOffRequest = new FullDayTimeOffRequest(
        LocalDate.parse("2024-06-01"),
        LocalDate.parse("2024-06-01")
    );

    assertEquals(timeOffRequest.getDuration(), Duration.ofHours(8));
  }

  @Test
  void shouldCalculate3DaysDuration() {
    var timeOffRequest = new FullDayTimeOffRequest(
        LocalDate.parse("2024-06-01"),
        LocalDate.parse("2024-06-03")
    );

    assertEquals(timeOffRequest.getDuration(), Duration.ofHours(24));
  }
}
