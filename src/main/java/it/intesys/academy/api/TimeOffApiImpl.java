package it.intesys.academy.api;

import it.intesys.intesys.academy.api.TimeOffApi;
import it.intesys.intesys.academy.dto.TimeOffRequestApiDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeOffApiImpl implements TimeOffApi {

    @Override
    public ResponseEntity<TimeOffRequestApiDTO> getTimeOffRequest(String requestId, String user) {
        return ResponseEntity.ok(
                new TimeOffRequestApiDTO().esito(TimeOffRequestApiDTO.EsitoEnum.PENDING)
        );
    }


}
