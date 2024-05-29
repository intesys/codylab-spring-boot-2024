package it.intesys.academy.mapper;

import it.intesys.academy.dto.UserDTO;
import it.intesys.academy.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class UserModelMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    static {

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

    }

    public static UserDTO fromEntityToDTO(User yUser) {

        return modelMapper.map(yUser, UserDTO.class);

    }

}
