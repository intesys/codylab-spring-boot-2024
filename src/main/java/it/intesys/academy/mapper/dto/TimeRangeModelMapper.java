package it.intesys.academy.mapper.dto;


import it.intesys.academy.dto.TimeRangeDTO;
import it.intesys.academy.model.TimeRange;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class TimeRangeModelMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public static TimeRangeDTO fromEntityToDTO(TimeRange timeRange) {
        return modelMapper.map(timeRange, TimeRangeDTO.class);
    }
}
