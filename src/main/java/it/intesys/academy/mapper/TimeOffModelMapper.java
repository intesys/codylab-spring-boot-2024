package it.intesys.academy.mapper;

import it.intesys.academy.model.TimeOff;
import it.intesys.intesys.academy.dto.TimeOffRequestApiDTO;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class TimeOffModelMapper {

    public static TimeOffRequestApiDTO fromEntityToAPIDTO(TimeOff timeOff) {
        TimeOffRequestApiDTO dto = ModelMapperUtils.modelMapper.map(timeOff, TimeOffRequestApiDTO.class);

        dto.setUtente(timeOff.getUser().getName() + " " + timeOff.getUser().getSurname());
        dto.setTipologia(timeOff.getType().name());
        dto.setPeriodo(new ArrayList<>(List.of(timeOff.getFrom(), timeOff.getTo())));
        if (!timeOff.getTimeRangeList().isEmpty()) {
            dto.setOrario(timeOff.getTimeRangeList().stream()
                    .map(timeRange -> List.of(
                            timeRange.getFrom().toString(),
                            timeRange.getTo().toString()
                    ))
                    .toList());
        }
        dto.setCreated( timeOff.getCreated().atOffset(ZoneOffset.UTC));
        dto.setEsito(TimeOffRequestApiDTO.EsitoEnum.valueOf(String.valueOf(timeOff.getResult())));

        return dto;
    }


    public static TimeOff fromAPIDTOtoEntity(TimeOffRequestApiDTO timeOffDTO) {
        return ModelMapperUtils.modelMapper.map(timeOffDTO, TimeOff.class);
    }

}
