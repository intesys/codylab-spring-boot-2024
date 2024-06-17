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

  private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;

  private @Column(name = "time_from") LocalTime from;

  private @Column(name = "time_to") LocalTime to;

  private @ManyToOne @JoinColumn(name = "PARTIAL_DAY_ID") PartialDayTimeOff partialDayTimeOff;

}
