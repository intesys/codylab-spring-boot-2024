package it.intesys.academy.utils;

import java.time.Duration;

public class StringUtils {

  public static String format(Duration duration) {
    return
        (duration.isNegative() ? "-" : "") +
            (duration.toHours() + "h " + duration.toMinutesPart() + "m");
  }

}
