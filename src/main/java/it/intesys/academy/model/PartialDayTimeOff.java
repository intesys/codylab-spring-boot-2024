package it.intesys.academy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PARTIAL_DAY_TIMEOFF")
public class PartialDayTimeOff {

  private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;

  private @Column LocalDate date;

  private @ManyToOne @JoinColumn(name = "USER_ID") User user;

  private @OneToMany(mappedBy = "partialDayTimeOff", fetch = FetchType.LAZY, cascade = CascadeType.ALL)  List<TimeRange> timeRangeList;

}
