package it.intesys.academy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TIME_RANGE")
public class TimeRange {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TIME_FROM")
    private LocalTime from;

    @Column(name = "TIME_TO")
    private LocalTime to;

    @ManyToOne @JoinColumn(name = "PARTIAL_DAY_ID")
    private PartialDayTimeOff partialDayTimeOff;

}
