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
  //@Sql(statements = {"insert into yuser ..."})
  //ARRANGE
  @Sql(scripts = {"/sql/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(scripts = {"/sql/deletedata.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  @DisplayName("GIVEN a user this full day time off WHEN find full day time off by user THEN a record is returned")
  void whenUserExistsAndHasFullDayTimeOffReturnResult() {
    //ACT
    List<FullDayTimeOff> fullDayTimeOffList =
        fullDayTimeOffRepository.findByUserId(1L);

    //ASSERT
    assertThat(fullDayTimeOffList).isNotEmpty().hasSize(1);
  }

  @Test
  //@Sql(statements = {"insert into yuser ..."})
  //ARRANGE
  @Sql(scripts = {"/sql/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(scripts = {"/sql/deletedata.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  @DisplayName("GIVEN a user does not exist WHEN find full day time off by user THEN an empty result is returned")
  void whenUserNotExistsAndHasFullDayTimeOffReturnEmpty() {
    //ACT
    List<FullDayTimeOff> fullDayTimeOffList =
        fullDayTimeOffRepository.findByUserId(10L);

    //ASSERT
    assertThat(fullDayTimeOffList).isNotNull().isEmpty();
  }
}