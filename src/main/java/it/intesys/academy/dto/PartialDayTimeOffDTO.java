package it.intesys.academy.dto;

import it.intesys.academy.model.TimeRange;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartialDayTimeOffDTO {

    private long id;
    private LocalDate timeOffDay;
    private List<TimeRange> timeRanges;
    private long userId;

}
