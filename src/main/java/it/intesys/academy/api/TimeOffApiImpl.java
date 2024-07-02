package it.intesys.academy.api;

import it.intesys.academy.service.TImeOffService;
import it.intesys.intesys.academy.api.TimeOffApi;
import it.intesys.intesys.academy.dto.TimeOffRequestApiDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeOffApiImpl implements TimeOffApi {

    private final TImeOffService timeOffService;

    public TimeOffApiImpl(TImeOffService timeOffService) {
        this.timeOffService = timeOffService;
    }

    @Override
    public ResponseEntity<TimeOffRequestApiDTO> getTimeOffRequest(String requestId, String user) {

        return ResponseEntity.ok(
                timeOffService.getTimeOffRequest(requestId, user)
        );
    }


}
