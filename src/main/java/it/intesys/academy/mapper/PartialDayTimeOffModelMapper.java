package it.intesys.academy.mapper;

import it.intesys.academy.dto.PartialDayTimeOffDTO;
import it.intesys.academy.model.PartialDayTimeOff;

import java.time.format.DateTimeFormatter;

public class PartialDayTimeOffModelMapper {

    public static PartialDayTimeOffDTO fromEntityToDTO(PartialDayTimeOff partialDayTimeOff) {

        PartialDayTimeOffDTO dto = ModelMapperFactory.modelMapper.map(partialDayTimeOff, PartialDayTimeOffDTO.class);

        dto.setDate( partialDayTimeOff.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) );

        return dto;

    }

}
