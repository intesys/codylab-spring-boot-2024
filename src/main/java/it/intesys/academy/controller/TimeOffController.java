//package it.intesys.academy.controller;
//
//import it.intesys.academy.dto.FullDayTimeOffAPIDTO;
//import it.intesys.academy.dto.FullDayTimeOffDTO;
//import it.intesys.academy.dto.PartialDayTimeOffAPIDTO;
//import it.intesys.academy.exceptions.BadRequestException;
//import it.intesys.academy.service.FullDayTimeOffService;
//import it.intesys.academy.service.PartialDayTimeOffService;
//import java.net.URI;
//import java.util.List;
//import java.util.Objects;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class TimeOffController {
//
//  private final PartialDayTimeOffService partialDayTimeOffService;
//
//  private final FullDayTimeOffService fullDayTimeOffService;
//
//  public TimeOffController(PartialDayTimeOffService partialDayTimeOffService, FullDayTimeOffService fullDayTimeOffService) {
//    this.partialDayTimeOffService = partialDayTimeOffService;
//    this.fullDayTimeOffService = fullDayTimeOffService;
//  }
//
//  /*
//  @GetMapping("/balance")
//  public TimeOffBalance getTimeOffRequests(@RequestParam("userId") Long userId) {
//    if (userId == null) {
//      throw new IllegalArgumentException("Missing user id");
//    }
//    return timeOffService.getTimeOffBalance(userId);
//  }
//   */
//
////  FullDayTimeOff
//
//  @GetMapping("/full-day-time-off-requests")
//  public ResponseEntity<List<FullDayTimeOffDTO>> getFullDayTimeOffRequests(@RequestHeader("user") Long userId) {
//    return ResponseEntity.ok(fullDayTimeOffService.getFullDayTimeOffRequests(userId));
//  }
//
//  @GetMapping("/full-day-time-off-request/{requestId}")
//  public ResponseEntity<FullDayTimeOffDTO> getFullDayTimeOffRequest(@PathVariable Long requestId,
//                                                                          @RequestHeader("user") Long userId) {
//    return ResponseEntity.ok(fullDayTimeOffService.getFullDayTimeOffRequest(requestId, userId));
//  }
//
//  @PostMapping("/full-day-time-off-requests")
//  public ResponseEntity<FullDayTimeOffAPIDTO> createNewFullDayTimeOffRequest(
//      @RequestBody @Validated FullDayTimeOffAPIDTO fullDayTimeOffDTO,
//      @RequestHeader("user") Long userId) {
//
//    FullDayTimeOffAPIDTO dto = fullDayTimeOffService.save(fullDayTimeOffDTO, userId);
//
//    return ResponseEntity
//        .created(URI.create("http://localhost:8080/full-day-time-off-request/" + dto.getId()))
//        .body(dto);
//  }
//
//  @PutMapping("/full-day-time-off-requests/{requestId}")
//  public ResponseEntity<FullDayTimeOffAPIDTO> updateFullDayTimeOffRequest(
//      @RequestBody @Validated FullDayTimeOffAPIDTO fullDayTimeOffDTO,
//      @PathVariable Long requestId,
//      @RequestHeader("user") Long userId) {
//
//    if (!Objects.equals(requestId, fullDayTimeOffDTO.getId())) {
//      throw new BadRequestException("Path id differs from the body id");
//    }
//
//    FullDayTimeOffAPIDTO dto = fullDayTimeOffService.update(fullDayTimeOffDTO, userId);
//    return ResponseEntity.ok(dto);
//  }
//
//  @DeleteMapping("/full-day-time-off-requests/{requestId}")
//  public ResponseEntity<Void> deleteFullDayTimeOffRequest(
//      @PathVariable Long requestId,
//      @RequestHeader("user") Long userId) {
//
//    fullDayTimeOffService.delete(requestId, userId);
//    return ResponseEntity.noContent().build();
//  }
//
////  PartialDayTimeOff
//  @GetMapping("/partial-day-time-off-requests")
//  public ResponseEntity<List<PartialDayTimeOffAPIDTO>> getPartialDayTimeOffRequests(@RequestHeader("user") Long userId) {
//
//    return ResponseEntity.ok(partialDayTimeOffService.getPartialDayTimeOffRequests(userId));
//  }
//
//  @GetMapping("/partial-day-time-off-request/{requestId}")
//  public ResponseEntity<PartialDayTimeOffAPIDTO> getPartialDayTimeOffRequest(@PathVariable Long requestId,
//                                                                             @RequestHeader("user") Long userId) {
//
//    return ResponseEntity.ok(partialDayTimeOffService.getPartialDayTimeOffRequest(requestId, userId));
//  }
//
//  @PostMapping("/partial-day-time-off-requests")
//  public ResponseEntity<PartialDayTimeOffAPIDTO> createNewPartialDayTimeOffRequest(
//      @RequestBody @Validated PartialDayTimeOffAPIDTO partialDayTimeOffDTO,
//      @RequestHeader("user") Long userId) {
//
//    partialDayTimeOffService.save(partialDayTimeOffDTO, userId);
//
//    return ResponseEntity
//          .created(URI.create("http://localhost:8080/partial-day-time-off-request/" + partialDayTimeOffDTO.getId()))
//          .body(partialDayTimeOffDTO);
//  }
//
//  @PutMapping("/partial-day-time-off-requests/{requestId}")
//    public ResponseEntity<PartialDayTimeOffAPIDTO> updatePartialDayTimeOffRequest(
//        @RequestBody @Validated PartialDayTimeOffAPIDTO partialDayTimeOffDTO,
//        @PathVariable Long requestId,
//        @RequestHeader("user") Long userId) {
//
//      if (!Objects.equals(requestId, partialDayTimeOffDTO.getId())) {
//          throw new BadRequestException("Path id differs from the body id");
//      }
//
//      PartialDayTimeOffAPIDTO dto = partialDayTimeOffService.update(partialDayTimeOffDTO, userId);
//      return ResponseEntity.ok(dto);
//    }
//
//    @DeleteMapping("/partial-day-time-off-requests/{requestId}")
//    public ResponseEntity<Void> deletePartialDayTimeOffRequest(
//        @PathVariable Long requestId,
//        @RequestHeader("user") Long userId) {
//
//      partialDayTimeOffService.delete(requestId, userId);
//      return ResponseEntity.noContent().build();
//    }
//
//}
