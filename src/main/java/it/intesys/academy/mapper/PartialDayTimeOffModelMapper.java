package it.intesys.academy.mapper;

import it.intesys.academy.dto.PartialDayTimeOffDTO;
import it.intesys.academy.model.PartialDayTimeOff;
import it.intesys.academy.dto.PartialDayTimeOffAPIDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PartialDayTimeOffModelMapper {

    public static PartialDayTimeOffDTO fromEntityToDTO(PartialDayTimeOff partialDayTimeOff) {

        PartialDayTimeOffDTO dto = ModelMapperUtils.modelMapper.map(partialDayTimeOff, PartialDayTimeOffDTO.class);

        dto.setDate( partialDayTimeOff.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) );

        return dto;

    }

    public static PartialDayTimeOff fromDTOtoEntity(PartialDayTimeOffDTO partialDayTimeOffDTO) {

        PartialDayTimeOff entity = ModelMapperUtils.modelMapper.map(partialDayTimeOffDTO, PartialDayTimeOff.class);

        entity.setDate( LocalDate.parse(partialDayTimeOffDTO.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd") ) );

        entity.setTimeRangeList( TimeRangeModelMapper.fromDTOstoEntities(partialDayTimeOffDTO.getTimeRangeDTOList(), entity) );

        return entity;

    }



    public static PartialDayTimeOffAPIDTO fromEntityToAPIDTO(PartialDayTimeOff partialDayTimeOff) {
        PartialDayTimeOffAPIDTO dto = ModelMapperUtils.modelMapper.map(partialDayTimeOff, PartialDayTimeOffAPIDTO.class);
        return dto;
    }


    public static PartialDayTimeOff fromAPIDTOtoEntity(PartialDayTimeOffAPIDTO partialDayTimeOffDTO) {
        PartialDayTimeOff entity = ModelMapperUtils.modelMapper.map(partialDayTimeOffDTO, PartialDayTimeOff.class);
        return entity;
    }

}
