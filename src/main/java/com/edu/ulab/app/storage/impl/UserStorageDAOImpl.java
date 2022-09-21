package com.edu.ulab.app.storage.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.mapper.DtoMappers.UserDtoMapper;
import com.edu.ulab.app.storage.UserStorage;
import com.edu.ulab.app.storage.DAO.UserStorageDAO;


public class UserStorageDAOImpl implements UserStorageDAO {

    UserStorage userStorage;

    UserDtoMapper userDtoMapper;


    @Override
    public UserDto getUserById(long id) {
        User user = userStorage.getUserById(id);

        if(user == null){
            throw new NotFoundException("User with id: " + id + " not found!");
        }
        return userDtoMapper.userToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userStorage.createUser(userDtoMapper.userDtoToUser(userDto));
        return userDtoMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userStorage.updateUser(userDtoMapper.userDtoToUser(userDto));
        return userDtoMapper.userToUserDto(user);
    }

    @Override
    public void deleteUserById(long id) {
        userStorage.deleteUserById(id);
    }

}
