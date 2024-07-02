package it.intesys.academy.service;

import it.intesys.academy.mapper.TimeOffModelMapper;
import it.intesys.academy.model.User;
import it.intesys.academy.repository.TimeOffRepository;
import it.intesys.academy.repository.UserRepository;
import it.intesys.academy.validator.TimeOffAPIDTOValidator;
import it.intesys.intesys.academy.dto.TimeOffRequestApiDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TImeOffService {

    private final UserRepository userRepository;
    private final TimeOffRepository timeOffRepository;
    private final TimeOffAPIDTOValidator timeOffAPIDTOValidator;

    public TImeOffService(UserRepository userRepository, TimeOffRepository timeOffRepository, TimeOffAPIDTOValidator timeOffAPIDTOValidator) {
        this.userRepository = userRepository;
        this.timeOffRepository = timeOffRepository;
        this.timeOffAPIDTOValidator = timeOffAPIDTOValidator;
    }

    public List<TimeOffRequestApiDTO> getTimeOffRequests(String user) {
        userRepository.findById(Long.parseLong(user))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return timeOffRepository.findByUserId(Long.parseLong(user))
                .stream()
                .map(TimeOffModelMapper::fromEntityToAPIDTO)
                .toList();
    }

    public TimeOffRequestApiDTO getTimeOffRequest(String requestId, String user) {
        userRepository.findById(Long.parseLong(user))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return timeOffRepository.findById(Long.parseLong(requestId))
                .map(TimeOffModelMapper::fromEntityToAPIDTO)
                .orElseThrow(() -> new IllegalArgumentException("Time off request not found"));
    }

    public TimeOffRequestApiDTO createTimeOffRequest(String userId, TimeOffRequestApiDTO timeOffRequestApiDTO) {
        User user = userRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        timeOffAPIDTOValidator.validate(timeOffRequestApiDTO);

        var timeOffRequest = TimeOffModelMapper.fromAPIDTOtoEntity(timeOffRequestApiDTO);
        timeOffRequest.setUser(user);

        timeOffRequest = timeOffRepository.save(timeOffRequest);

        return TimeOffModelMapper.fromEntityToAPIDTO(timeOffRequest);
    }
}
