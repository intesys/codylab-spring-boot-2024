package it.intesys.academy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TIMEOFF")
public class TimeOff {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;

    private @Enumerated(EnumType.STRING) @Column(name = "type", nullable = false) TypeEnum type;

    private @Enumerated(EnumType.STRING) @Column(name = "result", nullable = false) ResultEnum result;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    private @Column(name = "date_from", nullable = false) LocalDate from;

    private @Column(name = "date_to", nullable = false) LocalDate to;

    private @ManyToOne @JoinColumn(name = "USER_ID") User user;

    private @OneToMany(mappedBy = "timeOff", fetch = FetchType.LAZY, cascade = CascadeType.ALL)  List<TimeRange> timeRangeList;

    public enum TypeEnum {
        FULL_DAY,
        PARTIAL_DAY
    }

    public enum ResultEnum {
        APPROVED,
        REJECTED,
        PENDING
    }

}
