package it.intesys.academy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PARTIAL_DAY_TIMEOFF")
public class PartialDayTimeOff {

    private @Id long id;

    private @Column LocalDate date;

    private @ManyToOne @JoinColumn(name = "USER_ID") User user;

    private @OneToMany(mappedBy = "partialDayTimeOff", fetch = FetchType.EAGER) List<TimeRange> timeRangeList;

}
