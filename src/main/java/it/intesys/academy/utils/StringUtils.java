package it.intesys.academy.utils;

import java.time.Duration;

public class StringUtils {

  public static String format(Duration duration) {
    return duration.toHours() + " hours, " + duration.toMinutesPart() + " minutes";
  }

}
