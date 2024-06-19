package it.intesys.academy.service;

import it.intesys.academy.dto.FullDayTimeOffAPIDTO;
import it.intesys.academy.model.FullDayTimeOff;
import it.intesys.academy.model.User;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
  @DisplayName("GIVEN a user not present WHEN save data THEN an illegal argument exception is thrown")
  void whenUserNotExistsThenIllegalArgumentException() {
    Long idUser = -1L;
    // Arrange
    FullDayTimeOffAPIDTO fullDayTimeOffAPIDTO =
        new FullDayTimeOffAPIDTO(0,
            LocalDate.of(2024, 6, 21),
            LocalDate.of(2024, 6, 22));
    when(userRepository.findById(idUser)).thenReturn(Optional.empty());

    //ACT
    Executable act = () -> fullDayTimeOffService.save(fullDayTimeOffAPIDTO, idUser);

    // ASSERT
    Exception exception = assertThrows(IllegalArgumentException.class, act);
    assertThat(exception.getMessage()).isEqualTo("User not found");
  }

  @Test
  @DisplayName("GIVEN user exists WHEN save data THEN data are saved")
  void whenUserExistsThenSaveData() {
    Long idUser = -1L;
    // Arrange
    FullDayTimeOffAPIDTO fullDayTimeOffAPIDTO =
        new FullDayTimeOffAPIDTO(0,
            LocalDate.of(2024, 6, 21),
            LocalDate.of(2024, 6, 21));
    when(userRepository.findById(idUser))
        .thenReturn(Optional.of(new User(idUser, "username", "name", "surname", "mail")));
    when(fullDayTimeOffRepository.save(any()))
        .thenReturn(new FullDayTimeOff(1L, LocalDate.of(2024, 6, 21), LocalDate.of(2024, 6, 21), new User(idUser, "username", "name", "surname", "mail")));

    //ACT
    fullDayTimeOffService.save(fullDayTimeOffAPIDTO, idUser);

    // ASSERT
    verify(fullDayTimeOffRepository, times(1)).save(any());
  }
}