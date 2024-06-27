package it.intesys.academy.service;

import it.intesys.academy.dto.UserDTO;
import it.intesys.academy.mapper.UserModelMapper;
import it.intesys.academy.repository.UserRepository;
import it.intesys.intesys.academy.dto.UserApiDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserModelMapper::fromEntityToDTO).collect(Collectors.toList());
    }

    public UserDTO getUser(long id) {
        return UserModelMapper.fromEntityToDTO(userRepository.findById(id).get());
    }

    public List<UserApiDTO> getAllUsersApiDTO() {
        return userRepository.findAll().stream()
            .map(UserModelMapper::fromEntityToApiDTO)
            .collect(Collectors.toList());
    }

}
