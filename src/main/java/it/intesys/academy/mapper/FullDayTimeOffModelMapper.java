package it.intesys.academy.mapper;

import it.intesys.academy.dto.FullDayTimeOffAPIDTO;
import it.intesys.academy.dto.FullDayTimeOffDTO;
import it.intesys.academy.model.FullDayTimeOff;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FullDayTimeOffModelMapper {
  public static FullDayTimeOffDTO fromEntityToDTO(FullDayTimeOff fullDayTimeOff, String datePattern) {

    FullDayTimeOffDTO dto = ModelMapperUtils.modelMapper.map(fullDayTimeOff, FullDayTimeOffDTO.class);

    dto.setFrom(fullDayTimeOff.getFrom().format(DateTimeFormatter.ofPattern(datePattern)) );
    dto.setTo(fullDayTimeOff.getTo().format(DateTimeFormatter.ofPattern(datePattern)) );

    return dto;
  }

  public static FullDayTimeOff fromDTOtoEntity(FullDayTimeOffDTO fullDayTimeOffDTO) {

    FullDayTimeOff entity = ModelMapperUtils.modelMapper.map(fullDayTimeOffDTO, FullDayTimeOff.class);

    entity.setFrom( LocalDate.parse(fullDayTimeOffDTO.getFrom(), DateTimeFormatter.ofPattern("yyyy-MM-dd") ) );
    entity.setTo( LocalDate.parse(fullDayTimeOffDTO.getTo(), DateTimeFormatter.ofPattern("yyyy-MM-dd") ) );

    return entity;
  }

  public static FullDayTimeOffAPIDTO fromEntityToAPIDTO (FullDayTimeOff fullDayTimeOff) {
    return ModelMapperUtils.modelMapper.map(fullDayTimeOff, FullDayTimeOffAPIDTO.class);
  }

  public static FullDayTimeOff fromAPIDTOtoEntity(FullDayTimeOffAPIDTO fullDayTimeOffAPIDTO) {
    return ModelMapperUtils.modelMapper.map(fullDayTimeOffAPIDTO, FullDayTimeOff.class);
  }
}
