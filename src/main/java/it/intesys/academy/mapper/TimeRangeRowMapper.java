package it.intesys.academy.mapper;

import it.intesys.academy.model.TimeRange;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeRangeRowMapper implements RowMapper {

    @Override
    public TimeRange mapRow(ResultSet rs, int rowNum) throws SQLException {
        TimeRange timeRange = new TimeRange();
        timeRange.setId(rs.getLong("ID"));
        timeRange.setStart(rs.getTime("TIME_FROM").toLocalTime());
        timeRange.setEnd(rs.getTime("TIME_TO").toLocalTime());
        timeRange.setPartialDayId(rs.getLong("PARTIAL_DAY_ID"));
        return timeRange;
    }
}
