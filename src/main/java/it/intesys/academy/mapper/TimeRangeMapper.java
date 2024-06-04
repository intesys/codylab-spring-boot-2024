package it.intesys.academy.mapper;

import it.intesys.academy.model.TimeRange;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeRangeMapper implements RowMapper<TimeRange> {
    @Override
    public TimeRange mapRow(ResultSet rs, int rowNum) throws SQLException {

        TimeRange timeRange = new TimeRange();

        timeRange.setId(rs.getLong("id"));
        timeRange.setFrom(rs.getTime("time_from").toLocalTime());
        timeRange.setTo(rs.getTime("time_to").toLocalTime());

        return timeRange;
    }
}
