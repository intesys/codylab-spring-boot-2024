package it.intesys.academy.repository;

import it.intesys.academy.model.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface UserRepository extends ListCrudRepository<User, Long> {

  List<User> findAllByEmailContaining(String text);
}
