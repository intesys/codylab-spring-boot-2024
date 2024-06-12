package it.intesys.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartialDayTimeOffDTO {

    private long id;

    private String date;

    private List<TimeRangeDTO> timeRangeDTOList;

};
