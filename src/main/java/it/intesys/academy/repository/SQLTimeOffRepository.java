package it.intesys.academy.repository;

import it.intesys.academy.model.FullDayTimeOffRequest;
import it.intesys.academy.model.PartialDayTimeOffRequest;
import it.intesys.academy.model.TimeOffRequest;
import it.intesys.academy.model.TimeRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SQLTimeOffRepository implements TimeOffRepository {

    private static final Logger log = LoggerFactory.getLogger(SQLTimeOffRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public SQLTimeOffRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TimeOffRequest> getAllTimeOffRequestsFor(Long userId) {
        List<TimeOffRequest> timeOffRequests = new ArrayList<>();
        List<TimeRange> times = new ArrayList<>(List.of(
                new TimeRange(LocalTime.of(8, 0, 0), LocalTime.of(10, 0, 0)),
                new TimeRange(LocalTime.of(17, 0, 0), LocalTime.of(18, 0, 0))
        ));

        timeOffRequests.add(
                new FullDayTimeOffRequest(LocalDate.of(2024, 6, 8), LocalDate.of(2024, 6, 12))
        );
        timeOffRequests.add(
                new FullDayTimeOffRequest(LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 2))
        );
        timeOffRequests.add(
                new PartialDayTimeOffRequest(LocalDate.of(2024, 6, 8), times)
        );

        return timeOffRequests;
    }

    @Override
    public Duration getAvailableTimeOffForUser(Long userId) {
        Long availableTimeOff = jdbcTemplate.queryForObject(
                "SELECT available_time_off FROM yuser WHERE id = :userId",
                Map.of("userId", userId),
                Long.class
        );

        if (availableTimeOff == null) {
            return Duration.ZERO;
        }

        return Duration.ofDays(availableTimeOff);

    }


}
