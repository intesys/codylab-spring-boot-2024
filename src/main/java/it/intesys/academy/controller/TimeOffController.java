package it.intesys.academy.controller;

import it.intesys.academy.dto.FullDayTimeOffAPIDTO;
import it.intesys.academy.dto.FullDayTimeOffDTO;
import it.intesys.academy.exceptions.BadRequestException;
import it.intesys.academy.service.FullDayTimeOffService;
import it.intesys.academy.service.PartialDayTimeOffService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TimeOffController {

  private final PartialDayTimeOffService partialDayTimeOffService;
  private final FullDayTimeOffService fullDayTimeOffService;


  @GetMapping("/full-day-time-off-requests/{requestId}")
  public ResponseEntity<FullDayTimeOffAPIDTO> getFullDayTimeOffRequest(@PathVariable Long requestId,
                                                                       @RequestHeader Long userId) {

    return ResponseEntity.ok(fullDayTimeOffService.getFullDayTimeOffRequest(userId, requestId));
  }

  @PostMapping("/full-day-time-off-requests")
  public ResponseEntity<FullDayTimeOffAPIDTO> createFullDayTimeOffRequest(
          @RequestBody @Validated FullDayTimeOffAPIDTO fullDayTimeOffAPIDTO,
          @RequestHeader Long userId) {

    if (fullDayTimeOffAPIDTO.getId() != null) {
      throw new BadRequestException("Id in create request must be null");
    }


    if (fullDayTimeOffAPIDTO.getFrom().isAfter(fullDayTimeOffAPIDTO.getTo())) {
      throw new BadRequestException("From date must be before to date");
    }

    FullDayTimeOffAPIDTO fullDayTimeOffRequest = fullDayTimeOffService.createFullDayTimeOffRequest(fullDayTimeOffAPIDTO, userId);
    return ResponseEntity
            .created(URI.create("http://localhost:8080/api/full-day-time-off-requests/" + fullDayTimeOffRequest.getId()))
            .body(fullDayTimeOffRequest);
  }

  @PutMapping("/full-day-time-off-requests/{requestId}")
  public ResponseEntity<FullDayTimeOffAPIDTO> updateFullDayTimeOffRequest(
          @PathVariable Long requestId,
          @RequestBody @Validated FullDayTimeOffAPIDTO fullDayTimeOffAPIDTO,
          @RequestHeader Long userId) {


    FullDayTimeOffAPIDTO fullDayTimeOffRequest = fullDayTimeOffService.updateFullDayTimeOffRequest(requestId, fullDayTimeOffAPIDTO, userId);

    return ResponseEntity.ok(fullDayTimeOffRequest);
  }
}
