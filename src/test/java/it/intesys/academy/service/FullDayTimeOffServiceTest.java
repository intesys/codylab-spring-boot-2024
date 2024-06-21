package it.intesys.academy.service;

import it.intesys.academy.dto.FullDayTimeOffAPIDTO;
import it.intesys.academy.repository.FullDayTimeOffRepository;
import it.intesys.academy.repository.UserRepository;
import it.intesys.academy.validator.FullDayTimeOffAPIDTOValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FullDayTimeOffServiceTest {

    @Mock
    private FullDayTimeOffRepository fullDayTimeOffRepository;

    @Mock
    private UserRepository userRepository;

    private FullDayTimeOffService fullDayTimeOffService;

    @BeforeEach
    void init() {
        fullDayTimeOffService =
                new FullDayTimeOffService(fullDayTimeOffRepository, userRepository, new FullDayTimeOffAPIDTOValidator());
    }

    @Test
    @DisplayName("GIVEN a user not present WHEN save data THEN an illegal argument exception")
    void whenUserNotExistsThenIllegalArgumentException() {
        long userId = -1L;

        // Arrange
        FullDayTimeOffAPIDTO fullDayTimeOffAPIDTO =
                new FullDayTimeOffAPIDTO(0,
                LocalDate.of(2024, 6, 21),
                LocalDate.of(2024, 6, 22));
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        //ACT
        Executable act = () -> fullDayTimeOffService.save(fullDayTimeOffAPIDTO, userId);

        // ASSERT
        Exception exception = assertThrows(IllegalArgumentException.class, act);
        assertThat(exception.getMessage()).isEqualTo("User not found");
    }

    @Test
    void getFullDayTimeoff() {
    }

    @Test
    void save() {
    }

    @Test
    void testSave() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteFullDayTimeOff() {
    }

    @Test
    void getFullDayTimeOffById() {
    }
}