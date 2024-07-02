package it.intesys.academy.validator;

import it.intesys.intesys.academy.dto.TimeOffRequestApiDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Component
public class TimeOffAPIDTOValidator {
    public void validate(TimeOffRequestApiDTO timeOffRequestApiDTO) {
        if (timeOffRequestApiDTO == null) {
            throw new IllegalArgumentException("timeOffRequestApiDTO cannot be null");
        }

        if (timeOffRequestApiDTO.getUtente() == null || timeOffRequestApiDTO.getUtente().isEmpty()) {
            throw new IllegalArgumentException("Utente is required");
        }

        LocalDate startDate = Optional.ofNullable(timeOffRequestApiDTO.getPeriodo().get(0))
                .orElseThrow(() -> new IllegalArgumentException("from date cannot be null"));

        LocalDate endDate = Optional.ofNullable(timeOffRequestApiDTO.getPeriodo().get(1))
                .orElseThrow(() -> new IllegalArgumentException("end date cannot be null"));

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("from date cannot be after end date");
        }

        if (timeOffRequestApiDTO.getTipologia().equals("FULL_DAY")) {
            validateFullDay(timeOffRequestApiDTO);
        } else if (timeOffRequestApiDTO.getTipologia().equals("PARTIAL_DAY")) {
            validatePartialDay(timeOffRequestApiDTO);
        } else {
            throw new IllegalArgumentException("tipologia must be either FULL_DAY or PARTIAL_DAY");
        }

    }

    public void validateFullDay(TimeOffRequestApiDTO timeOffRequestApiDTO) {
        if (timeOffRequestApiDTO.getOrario() != null && !timeOffRequestApiDTO.getOrario().isEmpty()) {
            throw new IllegalArgumentException("orario cannot be set for FULL_DAY time off request");
        }
    }

    public void validatePartialDay(TimeOffRequestApiDTO timeOffRequestApiDTO) {
        if (timeOffRequestApiDTO.getOrario() == null || timeOffRequestApiDTO.getOrario().isEmpty()) {
            throw new IllegalArgumentException("orario cannot be null or empty for PARTIAL_DAY time off request");
        }

        for (List<String> timeRange : timeOffRequestApiDTO.getOrario()) {

            LocalTime startTime = LocalTime.parse(Optional.ofNullable(timeRange.get(0))
                    .orElseThrow(() -> new IllegalArgumentException("from time cannot be null")));

            LocalTime endTime = LocalTime.parse(Optional.ofNullable(timeRange.get(1))
                    .orElseThrow(() -> new IllegalArgumentException("end time cannot be null")));

            if (startTime.isAfter(endTime)) {
                throw new IllegalArgumentException("from time cannot be after end time");
            }
        }
    }
}