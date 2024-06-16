package it.intesys.academy.dto;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartialDayTimeOffAPIDTO {

    private long id;

    @Nonnull
    private LocalDate date;

    @Nonnull
    private List<TimeRangeDTO> timeRangeDTOList;

}
