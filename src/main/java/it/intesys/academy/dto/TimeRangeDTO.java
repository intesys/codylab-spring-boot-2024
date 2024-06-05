package it.intesys.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeRangeDTO {

    private Long id;
    private LocalTime start;
    private LocalTime end;
    private Long PartialDayId;

}
