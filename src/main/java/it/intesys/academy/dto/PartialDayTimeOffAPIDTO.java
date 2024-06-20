package it.intesys.academy.dto;

import jakarta.annotation.Nonnull;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PartialDayTimeOffAPIDTO {
    private Long id;

    @Nonnull
    private LocalDate date;

    @Nonnull
    private List<TimeRangeDTO> timeRangeAPIDTOList;

}
