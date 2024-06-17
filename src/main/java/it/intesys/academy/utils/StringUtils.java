package it.intesys.academy.utils;

import java.time.Duration;

public class StringUtils {

  public static String format(Duration duration) {
    return "%s%s".formatted(duration.isNegative() ? "-" : "", duration.toHours() + "h " + duration.toMinutesPart() + "m");
  }

}
