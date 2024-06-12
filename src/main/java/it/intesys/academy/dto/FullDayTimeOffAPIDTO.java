package it.intesys.academy.dto;

import jakarta.annotation.Nonnull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullDayTimeOffAPIDTO {
    private long id;

    @Nonnull
    private LocalDate from;

    @Nonnull
    private LocalDate to;
}
