package it.intesys.academy.mapper.dto;

import it.intesys.academy.dto.UserDTO;
import it.intesys.academy.mapper.ModelMapperFactory;
import it.intesys.academy.model.User;

public class UserModelMapper {

    public static UserDTO fromEntityToDTO(User yUser) {

        return ModelMapperFactory.modelMapper.map(yUser, UserDTO.class);

    }

}
