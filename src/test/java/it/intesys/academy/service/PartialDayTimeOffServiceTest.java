package it.intesys.academy.service;

import it.intesys.academy.dto.PartialDayTimeOffDTO;
import it.intesys.academy.dto.TimeRangeDTO;
import it.intesys.academy.repository.PartialDayTimeOffRepository;
import it.intesys.academy.repository.UserRepository;
import it.intesys.academy.validator.PartialDayTimeOffAPIDTOValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PartialDayTimeOffServiceTest {

    @Mock
    private PartialDayTimeOffRepository partialDayTimeOffRepository;

    @Mock
    private UserRepository userRepository;

    private PartialDayTimeOffService partialDayTimeOffService;

    @BeforeEach
    void init() {
        partialDayTimeOffService = new PartialDayTimeOffService(partialDayTimeOffRepository, userRepository, new PartialDayTimeOffAPIDTOValidator());
    }

    @Test
    @DisplayName("GIVEN a user not present WHEN save data THEN an illegal argument exception")
    void whenUserNotExsitsThenIllegalArgumentException() {
        long userId = -1L;

        // Arrange
        PartialDayTimeOffDTO partialDayTimeOffDTO = new PartialDayTimeOffDTO(
          0,
          "2024-06-21",
                List.of(new TimeRangeDTO(0, "08:00", "12:00"))
        );

        // Act
        Executable act = () -> partialDayTimeOffService.save(partialDayTimeOffDTO, userId);

        // Assert
        Exception exception = assertThrows(IllegalArgumentException.class, act);
        assertThat(exception.getMessage()).isEqualTo("User not fuond");
    }

    @Test
    void getPartialDayTimeoff() {
    }

    @Test
    void save() {
    }

    @Test
    void deletePartialDayTimeOff() {
    }
}