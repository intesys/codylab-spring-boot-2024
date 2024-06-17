package it.intesys.academy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FULL_DAY_TIMEOFF")
public class FullDayTimeOff {

  private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;

  private @Column(name = "date_from", nullable = false) LocalDate from;

  private @Column(name = "date_to", nullable = false) LocalDate to;

  private @ManyToOne @JoinColumn(name = "USER_ID") User user;

}
