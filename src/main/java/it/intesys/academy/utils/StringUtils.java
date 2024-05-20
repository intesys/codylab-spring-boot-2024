package it.intesys.academy.utils;

import java.time.Duration;

public class StringUtils {

    public static String formatTime(Duration duration) {
        return String.format("%s Hours, %s Minutes", duration.toHours(), duration.toMinutesPart());

    }
}
