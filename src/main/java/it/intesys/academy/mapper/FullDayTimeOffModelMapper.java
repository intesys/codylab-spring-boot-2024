package it.intesys.academy.mapper;

import it.intesys.academy.dto.FullDayTimeOffDTO;
import it.intesys.academy.model.FullDayTimeOff;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FullDayTimeOffModelMapper {

    public static FullDayTimeOffDTO fromEntityToDTO(FullDayTimeOff fullDayTimeOff) {

        FullDayTimeOffDTO dto = ModelMapperUtils.modelMapper.map(fullDayTimeOff, FullDayTimeOffDTO.class);

        dto.setFrom(fullDayTimeOff.getFrom().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) );
        dto.setTo(fullDayTimeOff.getTo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) );

        return dto;

    }

    public static FullDayTimeOff fromDTOtoEntity(FullDayTimeOffDTO fullDayTimeOffDTO) {

        FullDayTimeOff entity = ModelMapperUtils.modelMapper.map(fullDayTimeOffDTO, FullDayTimeOff.class);

        entity.setFrom( LocalDate.parse(fullDayTimeOffDTO.getFrom(), DateTimeFormatter.ofPattern("dd/MM/yyyy") ) );
        entity.setTo( LocalDate.parse(fullDayTimeOffDTO.getTo(), DateTimeFormatter.ofPattern("dd/MM/yyyy") ) );

        return entity;

    }

}