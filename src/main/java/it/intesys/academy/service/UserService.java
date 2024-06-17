package it.intesys.academy.service;

import it.intesys.academy.dto.UserDTO;
import it.intesys.academy.mapper.UserModelMapper;
import it.intesys.academy.model.User;
import it.intesys.academy.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserModelMapper::fromEntityToDTO).toList();
    }

    public List<UserDTO> getAllUsers(String text) {
        List<User> users;
        if (StringUtils.hasText(text)) {
            users = userRepository.findAllByEmailContaining(text);

        } else {
            users = userRepository.findAll();
        }
        return users.stream().map(UserModelMapper::fromEntityToDTO).toList();
    }

    public UserDTO getUser(long id) {
        return UserModelMapper.fromEntityToDTO(userRepository.findById(id).get());
    }

}
