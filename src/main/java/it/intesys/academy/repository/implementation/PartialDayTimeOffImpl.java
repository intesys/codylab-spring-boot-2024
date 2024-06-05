package it.intesys.academy.repository.implementation;

import it.intesys.academy.mapper.PartialDayTimeOffMapper;
import it.intesys.academy.model.PartialDayTimeOff;
import it.intesys.academy.repository.PartialDayTimeOffRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartialDayTimeOffImpl implements PartialDayTimeOffRepository {

    private final JdbcTemplate jdbcTemplate;

    public PartialDayTimeOffImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PartialDayTimeOff> findPartialDaysForUser(long idUser) {

        return jdbcTemplate.query("SELECT * FROM PARTIAL_DAY_TIMEOFF WHERE USER_ID = ?", new PartialDayTimeOffMapper(), idUser);

    }

    @Override
    public void deletePartialDayTimeOff(long id) {
        jdbcTemplate.update("DELETE FROM PARTIAL_DAY_TIMEOFF WHERE id = ?", id);
    }

}
