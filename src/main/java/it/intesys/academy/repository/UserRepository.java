package it.intesys.academy.repository;

import it.intesys.academy.model.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findUser(Long id);

}
