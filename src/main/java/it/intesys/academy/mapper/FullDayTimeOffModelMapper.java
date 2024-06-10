package it.intesys.academy.mapper;

import it.intesys.academy.dto.FullDayTimeOffDTO;
import it.intesys.academy.model.FullDayTimeOff;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FullDayTimeOffModelMapper {
    public static FullDayTimeOffDTO fromEntityToDTO(FullDayTimeOff fullDayTimeOff, String datePattern) {

        FullDayTimeOffDTO dto = ModelMapperUtils.modelMapper.map(fullDayTimeOff, FullDayTimeOffDTO.class);

        dto.setDateStart(fullDayTimeOff.getFrom().format(DateTimeFormatter.ofPattern(datePattern)) );
        dto.setDateEnd(fullDayTimeOff.getTo().format(DateTimeFormatter.ofPattern(datePattern)) );

        return dto;

    }


    public static FullDayTimeOff fromDTOtoEntity(FullDayTimeOffDTO fullDayTimeOffDTO) {

        FullDayTimeOff entity = ModelMapperUtils.modelMapper.map(fullDayTimeOffDTO, FullDayTimeOff.class);

        entity.setFrom( LocalDate.parse(fullDayTimeOffDTO.getDateStart(), DateTimeFormatter.ofPattern("yyyy-MM-dd") ) );
        entity.setTo( LocalDate.parse(fullDayTimeOffDTO.getDateEnd(), DateTimeFormatter.ofPattern("yyyy-MM-dd") ) );

        return entity;

    }
}
