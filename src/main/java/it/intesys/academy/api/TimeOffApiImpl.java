package it.intesys.academy.api;

import it.intesys.intesys.academy.api.TimeOffApi;
import it.intesys.intesys.academy.dto.TimeOffRequestApiDTO;
import it.intesys.intesys.academy.dto.TimeOffRequestApiDTO.EsitoEnum;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeOffApiImpl implements TimeOffApi {

  @Override
  public ResponseEntity<TimeOffRequestApiDTO> getTimeOffRequest(Long requestId, Long user) {
    return ResponseEntity.ok(
        new TimeOffRequestApiDTO().esito(EsitoEnum.ACCEPTED)
    );
  }

  @Override
  public ResponseEntity<TimeOffRequestApiDTO> createNewTimeOffRequest(Long user,
      TimeOffRequestApiDTO timeOffRequestApiDTO) {
    return TimeOffApi.super.createNewTimeOffRequest(user, timeOffRequestApiDTO);
  }

  @Override
  public ResponseEntity<Void> deleteTimeOffRequest(Long requestId, Long user) {
    return TimeOffApi.super.deleteTimeOffRequest(requestId, user);
  }

  @Override
  public ResponseEntity<List<TimeOffRequestApiDTO>> searchTimeOffRequests(String user, LocalDate from, LocalDate to) {
    return TimeOffApi.super.searchTimeOffRequests(user, from, to);
  }

  @Override
  public ResponseEntity<TimeOffRequestApiDTO> updateTimeOffRequest(Long requestId, Long user,
      TimeOffRequestApiDTO timeOffRequestApiDTO) {
    return TimeOffApi.super.updateTimeOffRequest(requestId, user, timeOffRequestApiDTO);
  }
}
