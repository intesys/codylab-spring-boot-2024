package it.intesys.academy.validator;

import it.intesys.academy.dto.FullDayTimeOffAPIDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FullDayTimeOffAPIDTOValidatorTest {

    private FullDayTimeOffAPIDTOValidator fullDayTimeOffAPIDTOValidator;

    @BeforeEach
    void init() {
        fullDayTimeOffAPIDTOValidator = new FullDayTimeOffAPIDTOValidator();
    }

    @Test
    @DisplayName("GIVEN a null object WHEN execute validation THEN an Illegal Argument Exception is thrown")
    void whenObjectIsNullThrowsIllegalArgumentException() {
        // Arrange
        FullDayTimeOffAPIDTO fullDayTimeOffAPIDTO = null;

        //ACT
        Executable act = () -> fullDayTimeOffAPIDTOValidator.validate(fullDayTimeOffAPIDTO);

        // ASSERT
        Exception exception = assertThrows(IllegalArgumentException.class, act);
        assertThat(exception.getMessage()).isEqualTo("fullDayTimeOffAPIDTO cannot be null");
    }

    @Test
    @DisplayName("GIVEN a valid object WHEN execute validation THEN no exception is thrown")
    void whenFullDayTimeIsOkThenShouldBeOK() {
        // Arrange
        FullDayTimeOffAPIDTO fullDayTimeOffAPIDTO =
                new FullDayTimeOffAPIDTO(0,
                        LocalDate.of(2024, 6, 21),
                        LocalDate.of(2024, 6, 22));

        // ACT
        boolean esito = false;
        try {
            fullDayTimeOffAPIDTOValidator.validate(fullDayTimeOffAPIDTO);
            esito = true;
        } catch (Exception e) {
            fail("Exception not expected");
        }

        // ASSERT
        assertThat(esito).isTrue();
    }

    @Test
    @DisplayName("GIVEN to date before a from date WHEN execute validation THEN an illegal argument exception is thrown")
    void whenFromDateIsAfterToDateThrowsIllegalArgumentException() {
        // Arrange
        FullDayTimeOffAPIDTO fullDayTimeOffAPIDTO =
                new FullDayTimeOffAPIDTO(0,
                        LocalDate.of(2024, 6, 23),
                        LocalDate.of(2024, 6, 22));

        //ACT
        Executable act = () -> fullDayTimeOffAPIDTOValidator.validate(fullDayTimeOffAPIDTO);

        // ASSERT
        Exception exception = assertThrows(IllegalArgumentException.class, act);
        assertThat(exception.getMessage()).isEqualTo("from date cannot be  cannot be null");
    }
}