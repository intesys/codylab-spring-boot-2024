package it.intesys.academy.api;

import it.intesys.academy.repository.UserRepository;
import it.intesys.academy.service.TImeOffService;
import it.intesys.academy.utils.SecurityUtils;
import it.intesys.intesys.academy.api.TimeOffApi;
import it.intesys.intesys.academy.dto.TimeOffRequestApiDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimeOffApiImpl implements TimeOffApi {

    private final TImeOffService timeOffService;
    private final UserRepository userRepository;

    public TimeOffApiImpl(TImeOffService timeOffService, UserRepository userRepository) {
        this.timeOffService = timeOffService;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<List<TimeOffRequestApiDTO>> getTimeOffRequests() {
        String username = SecurityUtils.getUsername();
        var user = userRepository.findByUsername(username)
                .orElseThrow();

        return ResponseEntity.ok(
                timeOffService.getTimeOffRequests(user.getId())
        );
    }

    @Override
    public ResponseEntity<TimeOffRequestApiDTO> getTimeOffRequest(String requestId) {
      String username = SecurityUtils.getUsername();
      var user = userRepository.findByUsername(username)
                 .orElseThrow();

        return ResponseEntity.ok(
                timeOffService.getTimeOffRequest(requestId, user.getId())
        );
    }

    @Override
    public ResponseEntity<TimeOffRequestApiDTO> createTimeOffRequest(TimeOffRequestApiDTO timeOffRequestApiDTO) {
      String username = SecurityUtils.getUsername();
      var user = userRepository.findByUsername(username)
                 .orElseThrow();

        TimeOffRequestApiDTO dto = timeOffService.createTimeOffRequest(user.getId(), timeOffRequestApiDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    public ResponseEntity<Void> deleteTimeOffRequest(String requestId) {
      String username = SecurityUtils.getUsername();
      var user = userRepository.findByUsername(username)
                 .orElseThrow();

        timeOffService.deleteTimeOffRequest(requestId, user.getId());
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<TimeOffRequestApiDTO> updateTimeOffRequest(String requestId, TimeOffRequestApiDTO timeOffRequestApiDTO) {
      String username = SecurityUtils.getUsername();
      var user = userRepository.findByUsername(username)
                 .orElseThrow();

        TimeOffRequestApiDTO dto = timeOffService.updateTimeOffRequest(timeOffRequestApiDTO, user.getId());
        return ResponseEntity.ok(dto);
    }

}
