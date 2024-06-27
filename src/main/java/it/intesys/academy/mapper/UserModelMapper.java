package it.intesys.academy.mapper;

import it.intesys.academy.dto.UserDTO;
import it.intesys.academy.model.User;
import it.intesys.intesys.academy.dto.UserApiDTO;

public class UserModelMapper {

    public static UserDTO fromEntityToDTO(User yUser) {

        return ModelMapperUtils.modelMapper.map(yUser, UserDTO.class);

    }

    public static UserApiDTO fromEntityToApiDTO(User yUser) {

        return ModelMapperUtils.modelMapper.map(yUser, UserApiDTO.class);

    }

}
