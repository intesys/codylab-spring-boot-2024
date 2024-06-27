package it.intesys.academy.api;

import it.intesys.academy.service.UserService;
import it.intesys.intesys.academy.api.UsersApi;
import it.intesys.intesys.academy.dto.UserApiDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersApiImpl implements UsersApi {

  private final UserService userService;

  public UsersApiImpl(UserService userService) {
    this.userService = userService;
  }

  @Override
  public ResponseEntity<List<UserApiDTO>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsersApiDTO());
  }
}
