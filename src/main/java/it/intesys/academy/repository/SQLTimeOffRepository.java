package it.intesys.academy.repository;

import it.intesys.academy.mapper.FullDayTimeOffRowMapper;
import it.intesys.academy.mapper.PartialDayTimeOffRowMapper;
import it.intesys.academy.mapper.TimeRangeRowMapper;
import it.intesys.academy.model.FullDayTimeOffRequest;
import it.intesys.academy.model.PartialDayTimeOffRequest;
import it.intesys.academy.model.TimeOffRequest;
import it.intesys.academy.model.TimeRange;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SQLTimeOffRepository implements TimeOffRepository {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public SQLTimeOffRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<TimeOffRequest> getAllTimeOffRequestsFor(Long userId) {
    List<TimeOffRequest> timeOffRequests = new ArrayList<>();

    timeOffRequests.add(
            new FullDayTimeOffRequest(
                    LocalDate.of(2024, Month.JULY, 1),
                    LocalDate.of(2024, Month.JULY, 1)
            )
    );

    timeOffRequests.add(
            new FullDayTimeOffRequest(
                    LocalDate.of(2024, Month.AUGUST, 12),
                    LocalDate.of(2024, Month.AUGUST, 16)
            )
    );

    timeOffRequests.add(
            new PartialDayTimeOffRequest(
                    LocalDate.of(2024, Month.JUNE, 15),
                    List.of(new TimeRange(LocalTime.of(12, 0), LocalTime.of(12, 30))
                    )
            )
    );

    return timeOffRequests;
  }

  @Override
  public Duration getAvailableTimeOffForUser(Long userId) {

    Long availableTimeOff = jdbcTemplate.queryForObject(
            "SELECT available_time_off from yuser where id = :userId", Map.of("userId", userId), Long.class);

    if (availableTimeOff == null) {
      return Duration.ZERO;
    }

    return Duration.ofDays(availableTimeOff);
  }

  @Override
  public List<FullDayTimeOffRequest> getFullDayTimeOffByUserId(Long userId) {

    return jdbcTemplate.query("SELECT * FROM FULL_DAY_TIMEOFF WHERE USER_ID = :userId", Map.of("userId", userId), new FullDayTimeOffRowMapper());

  }

  public List<PartialDayTimeOffRequest> getPartialDayTimeOffByUserId(Long userId) {
    List<PartialDayTimeOffRequest> partialDayTimeOffs = jdbcTemplate.query("SELECT * FROM PARTIAL_DAY_TIMEOFF WHERE USER_ID = :userId", Map.of("userId", userId), new PartialDayTimeOffRowMapper());

    for (PartialDayTimeOffRequest partialDayTimeOff : partialDayTimeOffs) {
      Long partialDayId = partialDayTimeOff.getId();
      List<TimeRange> timeRanges = getTimeRangesByPartialDayId(partialDayId);
      partialDayTimeOff.setTimeRanges(timeRanges);
    }

    return partialDayTimeOffs;
  }

  public List<TimeRange> getTimeRangesByPartialDayId(Long partialDayId) {
    return jdbcTemplate.query("SELECT * FROM TIME_RANGE WHERE PARTIAL_DAY_ID = :partialDayId", Map.of("partialDayId", partialDayId), new TimeRangeRowMapper());
  }
}
