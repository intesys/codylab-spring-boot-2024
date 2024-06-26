package it.intesys.academy.repository;

import it.intesys.academy.model.FullDayTimeOff;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class FullDayTimeOffRepositoryITest {

    @Autowired
    private FullDayTimeOffRepository fullDayTimeOffRepository;

    @Test
    @Sql(scripts = {"/sql/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//    @Transactional
    @Sql(scripts = {"/sql/deletedata.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("GIVEN a user exists and has full day time off WHEN findByUserId THEN return result")
    void whenUserExistsAndHasFullDayTimeOffReturnResult() {
        // Act
        List<FullDayTimeOff> fullDayTimeOffs = fullDayTimeOffRepository.findByUserId(1L);

        // Assert
        assertThat(fullDayTimeOffs).isNotNull().hasSize(1);
    }

    @Test
    @Sql(scripts = {"/sql/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//    @Transactional
    @Sql(scripts = {"/sql/deletedata.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("GIVEN a user not exists and has full day time off WHEN findByUserId THEN return result")
    void whenUserNotExistsAndHasFullDayTimeOffReturnResult() {
        // Act
        List<FullDayTimeOff> fullDayTimeOffs = fullDayTimeOffRepository.findByUserId(10L);

        // Assert
        assertThat(fullDayTimeOffs).isNotNull();
    }
}