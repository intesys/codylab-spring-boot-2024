package it.intesys.academy.api;

import it.intesys.academy.service.TImeOffService;
import it.intesys.intesys.academy.api.TimeOffApi;
import it.intesys.intesys.academy.dto.TimeOffRequestApiDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimeOffApiImpl implements TimeOffApi {

    private final TImeOffService timeOffService;

    public TimeOffApiImpl(TImeOffService timeOffService) {
        this.timeOffService = timeOffService;
    }

    @Override
    public ResponseEntity<List<TimeOffRequestApiDTO>> getTimeOffRequests(String user) {
        return ResponseEntity.ok(
                timeOffService.getTimeOffRequests(user)
        );
    }

    @Override
    public ResponseEntity<TimeOffRequestApiDTO> getTimeOffRequest(String requestId, String user) {
        return ResponseEntity.ok(
                timeOffService.getTimeOffRequest(requestId, user)
        );
    }

    @Override
    public ResponseEntity<TimeOffRequestApiDTO> createTimeOffRequest(String user, TimeOffRequestApiDTO timeOffRequestApiDTO) {
        TimeOffRequestApiDTO dto = timeOffService.createTimeOffRequest(user, timeOffRequestApiDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Override
    public ResponseEntity<Void> deleteTimeOffRequest(String requestId, String user) {
        timeOffService.deleteTimeOffRequest(requestId, user);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<TimeOffRequestApiDTO> updateTimeOffRequest(String requestId, String user, TimeOffRequestApiDTO timeOffRequestApiDTO) {
        TimeOffRequestApiDTO dto = timeOffService.updateTimeOffRequest(timeOffRequestApiDTO, user);
        return ResponseEntity.ok(dto);
    }

}
