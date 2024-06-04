package it.intesys.academy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartialDayTimeOff {

    private long id;

    private LocalDate date;

    private List<TimeRange> timeRangeList;

}
