package it.intesys.academy.repository;

import it.intesys.academy.model.User;

import java.util.List;

public interface UserRepository {

    public List<User> findAll();

}
