package it.intesys.academy.mapper;

import it.intesys.academy.model.PartialDayTimeOffRequest;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartialDayTimeOffRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        PartialDayTimeOffRequest partialDayTimeOffRequest = new PartialDayTimeOffRequest();

        partialDayTimeOffRequest.setId(rs.getLong("ID"));
        partialDayTimeOffRequest.setTimeOffDay(rs.getDate("DATE").toLocalDate());
        partialDayTimeOffRequest.setUserId(rs.getLong("USER_ID"));

        return partialDayTimeOffRequest;
    }
}
