package it.intesys.academy.validator;

import it.intesys.academy.dto.PartialDayTimeOffDTO;
import it.intesys.academy.dto.TimeRangeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PartialDayTimeOffAPIDTOValidatorTest {

    private PartialDayTimeOffAPIDTOValidator partialDayTimeOffAPIDTOValidator;

    @BeforeEach
    void init() {
        partialDayTimeOffAPIDTOValidator = new PartialDayTimeOffAPIDTOValidator();
    }

    @Test
    @DisplayName("GIVEN a null partialDayTimeOffDTO WHEN validate THEN an illegal argument exception")
    void whenPartialDayTimeOffDTOIsNullThenIllegalArgumentException() {
        // Arrange
        PartialDayTimeOffDTO partialDayTimeOffDTO = null;

        // Act
        Executable act = () -> partialDayTimeOffAPIDTOValidator.validate(partialDayTimeOffDTO);

        // Assert
        Exception exception = assertThrows(IllegalArgumentException.class, act);
        assertThat(exception.getMessage()).isEqualTo("partialDayTimeOffDTO cannot be null");
    }

    @Test
    @DisplayName("GIVEN a valid object WHEN execute validation THEN no exception is thrown")
    void whenPartialDayTimeOffDTOIsValidThenNoException() {
        // Arrange
        PartialDayTimeOffDTO partialDayTimeOffDTO = new PartialDayTimeOffDTO(
                0,
                "2024-06-21",
                List.of(new TimeRangeDTO(0, "08:00", "12:00"))
        );

        // Act
        boolean esito = false;
        try {
            partialDayTimeOffAPIDTOValidator.validate(partialDayTimeOffDTO);
            esito = true;
        } catch (Exception e) {
            fail("Exception not expected");
        }

        // Assert
        assertThat(esito).isTrue();
    }

    @Test
    @DisplayName("GIVEN end time before start time WHEN execute validation THEN an illegal argument exception is thrown")
    void whenEndTimeBeforeStartTimeThenIllegalArgumentException() {
        // Arrange
        PartialDayTimeOffDTO partialDayTimeOffDTO = new PartialDayTimeOffDTO(
                0,
                "2024-06-21",
                List.of(new TimeRangeDTO(0, "12:00", "08:00"))
        );

        // Act
        Executable act = () -> partialDayTimeOffAPIDTOValidator.validate(partialDayTimeOffDTO);

        // Assert
        Exception exception = assertThrows(IllegalArgumentException.class, act);
        assertThat(exception.getMessage()).isEqualTo("start time cannot be after end time");
    }

}