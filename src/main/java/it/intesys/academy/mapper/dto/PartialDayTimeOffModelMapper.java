package it.intesys.academy.mapper.dto;

import it.intesys.academy.dto.PartialDayTimeOffDTO;
import it.intesys.academy.model.PartialDayTimeOffRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class PartialDayTimeOffModelMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public static PartialDayTimeOffDTO fromEntityToDTO(PartialDayTimeOffRequest partialDayTimeOffRequest) {
        return modelMapper.map(partialDayTimeOffRequest, PartialDayTimeOffDTO.class);
    }
}
