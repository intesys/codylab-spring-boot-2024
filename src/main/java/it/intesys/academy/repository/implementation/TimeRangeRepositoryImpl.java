package it.intesys.academy.repository.implementation;

import it.intesys.academy.mapper.TimeRangeMapper;
import it.intesys.academy.model.TimeRange;
import it.intesys.academy.repository.TimeRangeRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TimeRangeRepositoryImpl implements TimeRangeRepository {

    private final JdbcTemplate jdbcTemplate;

    public TimeRangeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TimeRange> findTimeRange(long partialDayTimeoff) {

        return jdbcTemplate.query("SELECT * FROM TIME_RANGE WHERE PARTIAL_DAY_ID = ?", new TimeRangeMapper(), partialDayTimeoff);

    }

    @Override
    public void deleteTimeRange(long id) {
        jdbcTemplate.update("DELETE FROM TIME_RANGE WHERE id = ?", id);
    }

}
