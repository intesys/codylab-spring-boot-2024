package it.intesys.academy.repository.implementation;

import it.intesys.academy.mapper.UserRowMapper;
import it.intesys.academy.model.User;
import it.intesys.academy.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {

        return jdbcTemplate.query("SELECT * FROM YUSER", new UserRowMapper());

    }

}
