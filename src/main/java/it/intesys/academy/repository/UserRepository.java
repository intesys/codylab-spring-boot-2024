package it.intesys.academy.repository;

import it.intesys.academy.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Long> {

  Optional<User> findByUsername(String username);


}
