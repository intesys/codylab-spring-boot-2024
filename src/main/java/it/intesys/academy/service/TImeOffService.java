package it.intesys.academy.service;

import it.intesys.academy.mapper.TimeOffModelMapper;
import it.intesys.academy.repository.TimeOffRepository;
import it.intesys.academy.repository.UserRepository;
import it.intesys.intesys.academy.dto.TimeOffRequestApiDTO;
import org.springframework.stereotype.Service;

@Service
public class TImeOffService {

    private final UserRepository userRepository;
    private final TimeOffRepository timeOffRepository;

    public TImeOffService(UserRepository userRepository, TimeOffRepository timeOffRepository) {
        this.userRepository = userRepository;
        this.timeOffRepository = timeOffRepository;
    }

    public TimeOffRequestApiDTO getTimeOffRequest(String requestId, String user) {
        userRepository.findById(Long.parseLong(user))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return timeOffRepository.findById(Long.parseLong(requestId))
                .map(TimeOffModelMapper::fromEntityToAPIDTO)
                .orElseThrow(() -> new IllegalArgumentException("Time off request not found"));
    }

}
