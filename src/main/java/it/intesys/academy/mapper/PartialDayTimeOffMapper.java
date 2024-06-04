package it.intesys.academy.mapper;


import it.intesys.academy.model.PartialDayTimeOff;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PartialDayTimeOffMapper implements RowMapper<PartialDayTimeOff> {

    @Override
    public PartialDayTimeOff mapRow(ResultSet rs, int rowNum) throws SQLException {

        PartialDayTimeOff partialDayTimeOff = new PartialDayTimeOff();

        partialDayTimeOff.setId(rs.getLong("id"));
        partialDayTimeOff.setDate(rs.getDate("date").toLocalDate());

        return partialDayTimeOff;
    }

}
