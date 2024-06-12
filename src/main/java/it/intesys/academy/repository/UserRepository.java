package it.intesys.academy.repository;

import it.intesys.academy.model.User;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Long> {

}