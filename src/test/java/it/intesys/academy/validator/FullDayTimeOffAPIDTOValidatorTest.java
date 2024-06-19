package it.intesys.academy.validator;

import it.intesys.academy.dto.FullDayTimeOffAPIDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class FullDayTimeOffAPIDTOValidatorTest {

  private FullDayTimeOffAPIDTOValidator fullDayTimeOffAPIDTOValidator;


  @BeforeEach
  void init() {
    fullDayTimeOffAPIDTOValidator = new FullDayTimeOffAPIDTOValidator();
  }

  @Test
  void whenFullDayTimeIsOkThenShouldBeOK() {
    // Arrange
    FullDayTimeOffAPIDTO fullDayTimeOffAPIDTO =
        new FullDayTimeOffAPIDTO(0,
            LocalDate.of(2024, 6, 21),
            LocalDate.of(2024, 6, 22));

    //ACT
    boolean esito = false;
    fullDayTimeOffAPIDTOValidator.validate(fullDayTimeOffAPIDTO);
    esito = true;

    // ASSERT
    assertThat(esito).isTrue();
  }
}