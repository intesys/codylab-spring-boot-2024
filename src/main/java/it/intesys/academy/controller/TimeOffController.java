package it.intesys.academy.controller;

import it.intesys.academy.dto.FullDayTimeOffAPIDTO;
import it.intesys.academy.dto.FullDayTimeOffDTO;
import it.intesys.academy.exceptions.BadRequestException;
import it.intesys.academy.model.FullDayTimeOff;
import it.intesys.academy.service.FullDayTimeOffService;
import it.intesys.academy.service.PartialDayTimeOffService;
import it.intesys.academy.dto.PartialDayTimeOffAPIDTO;
import java.net.URI;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


//costruttore
@RestController
public class TimeOffController {

  private final PartialDayTimeOffService partialDayTimeOffService;

  private final FullDayTimeOffService fullDayTimeOffService;

  public TimeOffController(PartialDayTimeOffService partialDayTimeOffService, FullDayTimeOffService fullDayTimeOffService) {
    this.partialDayTimeOffService = partialDayTimeOffService;
    this.fullDayTimeOffService = fullDayTimeOffService;
  }

  //FULL DAY TIME OFF

  @GetMapping("/full-day-time-off-requests/{requestId}")
  public ResponseEntity<FullDayTimeOffAPIDTO> getFullDayTimeOffRequest(@PathVariable Long requestId,
                                                                       @RequestHeader Long userId) {
    return ResponseEntity.ok(fullDayTimeOffService.getFullDayTimeOffRequest(requestId, userId));
  }


  @PostMapping("/full-day-time-off-requests")
  public ResponseEntity<FullDayTimeOffAPIDTO> createNewFullDayTimeOffRequest(
      @RequestBody @Validated FullDayTimeOffAPIDTO fullDayTimeOffDTO,
      @RequestHeader("user") Long userId) {
    FullDayTimeOffAPIDTO dto = fullDayTimeOffService.save(fullDayTimeOffDTO, userId);
    return ResponseEntity
        .created(URI.create("http://localhost:8080/full-day-time-off-request/" + dto.getId()))
        .body(dto);
  }

  @PutMapping("/full-day-time-off-requests/{requestId}")
  public ResponseEntity<FullDayTimeOffAPIDTO> updateFullDayTimeOffRequest(
      @RequestBody @Validated FullDayTimeOffAPIDTO fullDayTimeOffDTO,
      @PathVariable Long requestId,
      @RequestHeader("user") Long userId) {

    if (!Objects.equals(requestId, fullDayTimeOffDTO.getId())) {
      throw new BadRequestException("Path id differs from the body id");
    }

    FullDayTimeOffAPIDTO dto = fullDayTimeOffService.update(fullDayTimeOffDTO, userId);
    return ResponseEntity.ok(dto);
  }

  // @DeleteMapping


  //PARTIAL DAY TIME OFF

  @GetMapping ("/partial-day-time-off-requests/{requestId}")
  public ResponseEntity<PartialDayTimeOffAPIDTO> getPartialDayTimeOffRequest(@PathVariable Long requestId,
                                                                       @RequestHeader Long userId) {
    return ResponseEntity.ok(partialDayTimeOffService.getPartialDayTimeOffRequest(requestId, userId));
  }
}
