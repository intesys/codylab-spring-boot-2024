package it.intesys.academy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeRange {

    private long id;

    private LocalTime from;

    private LocalTime to;

}
