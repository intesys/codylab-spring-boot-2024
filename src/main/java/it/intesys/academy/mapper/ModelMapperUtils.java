package it.intesys.academy.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperUtils {

    public static final ModelMapper modelMapper = new ModelMapper();

    static {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

    }

}
