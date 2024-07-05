package it.intesys.academy.api;

import it.intesys.academy.repository.UserRepository;
import it.intesys.academy.utils.SecurityUtils;
import it.intesys.intesys.academy.api.TimeOffApi;
import it.intesys.intesys.academy.dto.TimeOffRequestApiDTO;
import it.intesys.intesys.academy.dto.TimeOffRequestApiDTO.EsitoEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeOffApiImpl implements TimeOffApi {

  private final UserRepository userRepository;

  public TimeOffApiImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public ResponseEntity<TimeOffRequestApiDTO> getTimeOffRequest(Long requestId) {
    String username = SecurityUtils.getUsername();
    var user = userRepository.findByUsername(username)
        .orElseThrow();

    System.out.println("Found user " + user.getId());
    return ResponseEntity.ok(
        new TimeOffRequestApiDTO().esito(EsitoEnum.ACCEPTED)
    );
  }


}
