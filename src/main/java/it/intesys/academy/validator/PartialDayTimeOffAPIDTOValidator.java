package it.intesys.academy.validator;

import it.intesys.academy.dto.PartialDayTimeOffDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Component
public class PartialDayTimeOffAPIDTOValidator {

    public void validate(PartialDayTimeOffDTO partialDayTimeOffDTO) {
        if (partialDayTimeOffDTO == null) {
            throw new IllegalArgumentException("partialDayTimeOffDTO cannot be null");
        }
        LocalDate.parse(Optional.of(partialDayTimeOffDTO.getDate())
                .orElseThrow(() -> new IllegalArgumentException("date cannot be null")));

        LocalTime startTime = LocalTime.parse(Optional.of(partialDayTimeOffDTO.getTimeRangeDTOList().getFirst().getFrom())
                .orElseThrow(() -> new IllegalArgumentException("start time cannot be null")));

        LocalTime endTime = LocalTime.parse(Optional.of(partialDayTimeOffDTO.getTimeRangeDTOList().getFirst().getTo())
                .orElseThrow(() -> new IllegalArgumentException("end time cannot be null")));


        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("start time cannot be after end time");
        }
    }
}
