package it.intesys.academy.controller;

import it.intesys.academy.dto.UserDTO;
import it.intesys.academy.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public ResponseEntity<List<UserDTO>> getAllUsers(
          @RequestParam(required = false) String text
  ) {

    return ResponseEntity.ok(userService.getAllUsers(text));
  }

}
