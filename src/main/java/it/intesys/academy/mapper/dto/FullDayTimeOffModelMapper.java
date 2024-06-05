package it.intesys.academy.mapper.dto;

import it.intesys.academy.dto.FullDayTimeOffDTO;
import it.intesys.academy.model.FullDayTimeOffRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class FullDayTimeOffModelMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public static FullDayTimeOffDTO fromEntityToDTO(FullDayTimeOffRequest fullDayTimeOffRequest) {
        return modelMapper.map(fullDayTimeOffRequest, FullDayTimeOffDTO.class);
    }
}
