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

    public List<TimeOffRequestApiDTO> getTimeOffRequests(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return timeOffRepository.findByUserId(userId)
                .stream()
                .map(TimeOffModelMapper::fromEntityToAPIDTO)
                .toList();
    }

    public TimeOffRequestApiDTO getTimeOffRequest(String requestId, Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return timeOffRepository.findById(Long.parseLong(requestId))
                .map(TimeOffModelMapper::fromEntityToAPIDTO)
                .orElseThrow(() -> new IllegalArgumentException("Time off request not found"));
    }

    public TimeOffRequestApiDTO createTimeOffRequest(Long userId, TimeOffRequestApiDTO timeOffRequestApiDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        timeOffAPIDTOValidator.validate(timeOffRequestApiDTO);

        var timeOffRequest = TimeOffModelMapper.fromAPIDTOtoEntity(timeOffRequestApiDTO);
        timeOffRequest.setUser(user);

        timeOffRequest = timeOffRepository.save(timeOffRequest);

        return TimeOffModelMapper.fromEntityToAPIDTO(timeOffRequest);
    }

    public void deleteTimeOffRequest(String requestId, Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        timeOffRepository.deleteById(Long.parseLong(requestId));
    }

    public TimeOffRequestApiDTO updateTimeOffRequest(TimeOffRequestApiDTO timeOffRequestApiDTO, Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        timeOffAPIDTOValidator.validate(timeOffRequestApiDTO);

        var timeOffRequest = TimeOffModelMapper.fromAPIDTOtoEntity(timeOffRequestApiDTO);
        timeOffRequest.setUser(userRepository.findById(userId).orElseThrow());

        timeOffRequest = timeOffRepository.save(timeOffRequest);

        return TimeOffModelMapper.fromEntityToAPIDTO(timeOffRequest);
    }
}
