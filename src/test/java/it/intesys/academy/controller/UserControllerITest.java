package it.intesys.academy.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class UserControllerITest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Given a user in DB When get user data THEN response has data")
  @Sql(scripts = {"/sql/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Transactional
  void whenUserExistsReturnDetail() {
    try {
      mockMvc.perform(get("/users/1"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id", equalTo(1)))
          .andExpect(jsonPath("$.username", equalTo("eoliosi")))
          .andExpect(jsonPath("$.name", equalTo("enrico")))
          .andExpect(jsonPath("$.surname", equalTo("oliosi")))
          .andExpect(jsonPath("$.email", equalTo("enrico.oliosi@intesys.it")))
      ;
    } catch (Exception e) {
      fail("No exception is expected here");
    }
  }

  @Test
  @DisplayName("Given a user that not exists When get user data THEN response is an error response case ")
  @Sql(scripts = {"/sql/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Transactional
  void whenUserNotExistsThrowsError() {
    try {
      mockMvc.perform(get("/users/-2"))
          .andDo(print())
          .andExpect(status().is5xxServerError())
          .andExpect(jsonPath("$.title", equalTo("An unknown error has occurred")))
      ;
    } catch (Exception e) {
      fail("No exception is expected here");
    }
  }
}