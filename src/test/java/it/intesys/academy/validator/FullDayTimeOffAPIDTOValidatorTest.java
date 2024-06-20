package it.intesys.academy.validator;

import it.intesys.academy.dto.FullDayTimeOffAPIDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
class FullDayTimeOffAPIDTOValidatorTest {

  private FullDayTimeOffAPIDTOValidator fullDayTimeOffAPIDTOValidator;

  @BeforeEach
  void init() {
    fullDayTimeOffAPIDTOValidator = new FullDayTimeOffAPIDTOValidator();
  }

  @Test
  @DisplayName("GIVEN a null FullDayTimeOffAPIDTO WHEN executing validation THEN should throw IllegalArgumentException")
  void whenFullDayTimeOffAPIDTOisNullThrowIllegalArgumentException() {

    // Arrange
    FullDayTimeOffAPIDTO fullDayTimeOffAPIDTO = null;

    // Act
    Executable act = () -> fullDayTimeOffAPIDTOValidator.validate(fullDayTimeOffAPIDTO);

    // Assert
    Exception exception = assertThrows(IllegalArgumentException.class, act);
    assertThat(exception.getMessage()).isEqualTo("fullDayTimeOffAPIDTO cannot be null");

  }

  @Test
  @DisplayName("GIVEN a valid FullDayTimeOffAPIDTO WHEN executing validation THEN should not throw any exception")
  void whenFullDayTimeOffAPIDTOisOkThenShouldBeOk() {
    // Arrange
    FullDayTimeOffAPIDTO fullDayTimeOffAPIDTO = new FullDayTimeOffAPIDTO(
            1L,
            LocalDate.of(2024, 6, 21),
            LocalDate.of(2024, 6, 22)
    );

    // Act
    boolean outcome = false;
    try {
      fullDayTimeOffAPIDTOValidator.validate(fullDayTimeOffAPIDTO);
      outcome = true;
    } catch (Throwable t) {
      fail("Should not throw any exception");
    }
    assertThat(outcome).isTrue();  // Assert
  }

  @Test
  @DisplayName("GIVEN a FullDayTimeOffAPIDTO with from date after to date WHEN executing validation THEN should throw IllegalArgumentException")
  void whenDateToIsAfterDateFromThenThrowIllegalArgumentException() {
    // Arrange
    FullDayTimeOffAPIDTO fullDayTimeOffAPIDTO = new FullDayTimeOffAPIDTO(
            1L,
            LocalDate.of(2024, 6, 22),
            LocalDate.of(2024, 6, 21)
    );

    // Act
    Executable act = () -> fullDayTimeOffAPIDTOValidator.validate(fullDayTimeOffAPIDTO);

    // Assert

    Exception exception = assertThrows(IllegalArgumentException.class, act);
    assertThat(exception.getMessage()).isEqualTo("from date cannot be cannot be null");
  }


}