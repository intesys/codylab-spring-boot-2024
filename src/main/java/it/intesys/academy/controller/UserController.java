package it.intesys.academy.controller;

import it.intesys.academy.dto.UserDTO;
import it.intesys.academy.model.User;
import it.intesys.academy.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public List<UserDTO> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long userId) {
    UserDTO userDTO = userService.getUser(userId);
    return ResponseEntity.ok(userDTO);
  }

}
