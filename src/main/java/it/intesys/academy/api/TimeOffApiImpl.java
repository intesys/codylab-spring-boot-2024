package it.intesys.academy.api;

import it.intesys.academy.repository.UserRepository;
import it.intesys.intesys.academy.api.TimeOffApi;
import it.intesys.intesys.academy.dto.TimeOffRequestApiDTO;
import it.intesys.intesys.academy.dto.TimeOffRequestApiDTO.EsitoEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeOffApiImpl implements TimeOffApi {

  private final UserRepository userRepository;

  public TimeOffApiImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public ResponseEntity<TimeOffRequestApiDTO> getTimeOffRequest(Long requestId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User principal = (User) authentication.getPrincipal();
    String username = principal.getUsername();
    var user = userRepository.findByUsername(username)
        .orElseThrow();

    System.out.println("Found user " + user.getId());
    return ResponseEntity.ok(
        new TimeOffRequestApiDTO().esito(EsitoEnum.ACCEPTED)
    );
  }

}
