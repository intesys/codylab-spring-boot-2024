package it.intesys.academy.repository;

import it.intesys.academy.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
// Cos'è @ActiveProfiles?
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void insertAndSelectUserTest() {
    // Arrange
    User user = new User(-1L, "username", "name", "surname", "email");

    // Act
    userRepository.save(user);

    // Assert
    Optional<User> userFromDb = userRepository.findById(-1L);

    assertThat(userFromDb).isPresent();

    boolean assertions = user.getId() == userFromDb.get().getId() &&
        user.getUsername().equals(userFromDb.get().getUsername()) &&
        user.getName().equals(userFromDb.get().getName()) &&
        user.getSurname().equals(userFromDb.get().getSurname()) &&
        user.getEmail().equals(userFromDb.get().getEmail());

    assertThat(assertions).isTrue();
  }


}