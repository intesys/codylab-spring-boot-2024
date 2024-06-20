package it.intesys.academy.repository;

import it.intesys.academy.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryITest {

  @Autowired
  private UserRepository userRepository;

  @Test
  @DisplayName("Testing insert e select user data lifecycle")
  void insertAndSelectUSerTest() {
    User user = new User(-1L, "username", "name", "surname", "mail");

    userRepository.save(user);
    Optional<User> userFromDb = userRepository.findById(-1L);

    UserComparator comparator = UserComparator.with(user);
    assertThat(userFromDb).isPresent();
    assertThat(userFromDb.get()).matches(comparator::hasSameFields);
  }

  private static class UserComparator {

    private User expectedUser;

    static UserComparator with(User expectedUser) {
      UserComparator comparator = new UserComparator();
      comparator.expectedUser = expectedUser;
      return comparator;
    }

    public boolean hasSameFields(User user) {
      if (expectedUser == user) return true;
      return user.getId() == expectedUser.getId() &&
          Objects.equals(user.getUsername(), expectedUser.getUsername()) &&
          Objects.equals(user.getName(), expectedUser.getName()) &&
          Objects.equals(user.getSurname(), expectedUser.getSurname()) &&
          Objects.equals(user.getEmail(), expectedUser.getEmail());
    }
  }

}