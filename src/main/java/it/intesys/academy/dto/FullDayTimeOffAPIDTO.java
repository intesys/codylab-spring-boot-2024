package it.intesys.academy.dto;

import java.time.LocalDate;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullDayTimeOffAPIDTO {

    private Long id;

    @Nonnull
    private LocalDate from;

    @Nonnull
    private LocalDate to;
}
