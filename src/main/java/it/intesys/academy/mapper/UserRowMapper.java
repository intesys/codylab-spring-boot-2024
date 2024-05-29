package it.intesys.academy.mapper;


import it.intesys.academy.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        var yUser = new User();

        yUser.setId(rs.getLong("id"));
        yUser.setName(rs.getString("name"));
        yUser.setSurname(rs.getString("surname"));
        yUser.setEmail(rs.getString("email"));
        yUser.setUsername(rs.getString("username"));

        return yUser;
    }

}
