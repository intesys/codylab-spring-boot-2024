package it.intesys.academy.validator;

import it.intesys.academy.dto.FullDayTimeOffAPIDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class FullDayTimeOffAPIDTOValidator {

  public void validate(FullDayTimeOffAPIDTO fullDayTimeOffAPIDTO) {
    if (fullDayTimeOffAPIDTO == null) {
      throw new IllegalArgumentException("fullDayTimeOffAPIDTO cannot be null");
    }
    LocalDate startDate = Optional.ofNullable(fullDayTimeOffAPIDTO.getFrom())
        .orElseThrow(() -> new IllegalArgumentException("from date cannot be null"));
    LocalDate endDate = Optional.ofNullable(fullDayTimeOffAPIDTO.getTo())
        .orElseThrow(() -> new IllegalArgumentException("end date cannot be null"));
    if (startDate.isAfter(endDate)) {
      throw new IllegalArgumentException("from date cannot be  cannot be null");
    }
  }
}
