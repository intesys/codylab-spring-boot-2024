package it.intesys.academy.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
// Perchè qua utilizziamo SpringBootTest e ActiveProfiles?
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
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
              .andExpect(jsonPath("$.email", equalTo("enrico.oliosi@intesys.it")));

    } catch (Exception e) {
      fail("No exception expected");
    }

  }

  @Test
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
      fail("No exception expected");
    }
  }
}