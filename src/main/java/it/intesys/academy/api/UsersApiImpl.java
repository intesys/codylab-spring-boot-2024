package it.intesys.academy.api;

import it.intesys.intesys.academy.api.UsersApi;
import it.intesys.intesys.academy.dto.UserApiDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersApiImpl implements UsersApi {

    @Override
    public ResponseEntity<List<UserApiDTO>> getAllUsers() {
        return UsersApi.super.getAllUsers();
    }

    @Override
    public ResponseEntity<UserApiDTO> getUser(Long id) {
        return UsersApi.super.getUser(id);
    }
}
