package it.intesys.academy.mapper.dto;

import it.intesys.academy.dto.TimeRangeDTO;
import it.intesys.academy.mapper.ModelMapperFactory;
import it.intesys.academy.model.TimeRange;

public class TimeRangeModelMapper {

    public static TimeRangeDTO fromEntityToDTO(TimeRange timeRange) {

        return ModelMapperFactory.modelMapper.map(timeRange, TimeRangeDTO.class);

    }

}
