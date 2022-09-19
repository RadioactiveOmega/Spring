package com.edu.ulab.app.mapper.DtoMappers;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
}
