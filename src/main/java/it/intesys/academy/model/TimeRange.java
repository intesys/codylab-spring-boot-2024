package it.intesys.academy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TIME_RANGE")
public class TimeRange {

    private @Id long id;

    private @Column(name = "time_from") LocalTime from;

    private @Column(name = "time_to") LocalTime to;

    private @ManyToOne @JoinColumn(name = "PARTIAL_DAY_ID") PartialDayTimeOff partialDayTimeOff;

}
