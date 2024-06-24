package it.intesys.academy.controller;

import it.intesys.academy.repository.FullDayTimeOffRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class TimeOffControllerITest {

  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  private FullDayTimeOffRepository fullDayTimeOffRepository;

  @Test
  @Sql(scripts = {"/sql/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Transactional
  void test() {
    try {
      LocalDate date = LocalDate.of(2024, 6, 24);
      System.out.println(date);
      mockMvc.perform(post("/full-day-time-off-requests")
              .header("user", 1L)
              .contentType("application/json")
              .content("{\"id\": 0, \"from\": \"2024-06-24\", \"to\": \"2024-06-25\"}"))
          .andDo(print())
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.from", equalTo("2024-06-24")))
          .andExpect(jsonPath("$.to", equalTo("2024-06-25")))
          ;
      verify(fullDayTimeOffRepository, times(1)).save(any());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}