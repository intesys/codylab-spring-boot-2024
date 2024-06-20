package it.intesys.academy.dto;

import jakarta.annotation.Nonnull;

import java.time.LocalDate;

public class TimeRangeAPIDTO {

    private Long id;

    @Nonnull
    private LocalDate from;

    @Nonnull
    private LocalDate to;

}
