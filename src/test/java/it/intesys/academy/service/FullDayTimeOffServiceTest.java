package it.intesys.academy.service;

import it.intesys.academy.dto.FullDayTimeOffAPIDTO;
import it.intesys.academy.model.FullDayTimeOff;
import it.intesys.academy.model.User;
import it.intesys.academy.repository.FullDayTimeOffRepository;
import it.intesys.academy.repository.UserRepository;
import it.intesys.academy.validator.FullDayTimeOffAPIDTOValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FullDayTimeOffServiceTest {

  @Mock
  private FullDayTimeOffRepository fullDayTimeOffRepository;
  @Mock
  private UserRepository userRepository;

  private FullDayTimeOffService fullDayTimeOffService;
  private FullDayTimeOffAPIDTOValidator fullDayTimeOffAPIDTOValidator;

  @BeforeEach
  void init() {
    fullDayTimeOffService = new FullDayTimeOffService(fullDayTimeOffRepository, userRepository, fullDayTimeOffAPIDTOValidator);
  }

  @Test
  void whenUserExistsThenSaveData() {
    // Arrange
    Long userId = 1L;
    LocalDate from = LocalDate.of(2024, 6, 21);
    LocalDate to = LocalDate.of(2024, 6, 22);

    FullDayTimeOffAPIDTO fullDayTimeOffAPIDTO = new FullDayTimeOffAPIDTO(
            userId,
            from,
            to
    );
    User user = new User(userId, "username", "name", "surname", "mail");

    // Act
    when(userRepository.findById(userId))
            .thenReturn(Optional.of(user));
    when(fullDayTimeOffRepository.save(any()))
            .thenReturn(new FullDayTimeOff(1L, from, to, user));

    fullDayTimeOffService.save(fullDayTimeOffAPIDTO, userId);

    // Assert
    verify(fullDayTimeOffRepository, times(1)).save(any());
  }
}