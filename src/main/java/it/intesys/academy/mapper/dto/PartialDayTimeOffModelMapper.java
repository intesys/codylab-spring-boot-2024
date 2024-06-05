package it.intesys.academy.mapper.dto;

import it.intesys.academy.dto.PartialDayTimeOffDTO;
import it.intesys.academy.mapper.ModelMapperFactory;
import it.intesys.academy.model.PartialDayTimeOff;

public class PartialDayTimeOffModelMapper {

    public static PartialDayTimeOffDTO fromEntityToDTO(PartialDayTimeOff partialDayTimeOff) {

        return ModelMapperFactory.modelMapper.map(partialDayTimeOff, PartialDayTimeOffDTO.class);

    }

}
