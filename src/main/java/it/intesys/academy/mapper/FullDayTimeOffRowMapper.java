package it.intesys.academy.mapper;

import it.intesys.academy.model.FullDayTimeOffRequest;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FullDayTimeOffRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        FullDayTimeOffRequest fullDayTimeOffRequest = new FullDayTimeOffRequest();

        fullDayTimeOffRequest.setId(rs.getLong("ID"));
        fullDayTimeOffRequest.setStart(rs.getDate("DATE_FROM").toLocalDate());
        fullDayTimeOffRequest.setEnd(rs.getDate("DATE_TO").toLocalDate());
        fullDayTimeOffRequest.setUserId(rs.getLong("USER_ID"));

        return fullDayTimeOffRequest;
    }
}
