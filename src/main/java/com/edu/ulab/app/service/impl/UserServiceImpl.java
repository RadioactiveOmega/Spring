package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.exception.CreationFailedExeption;
import com.edu.ulab.app.exception.DeletionFailedExeption;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.exception.UpdateFailedExeption;
import com.edu.ulab.app.service.UserService;
import com.edu.ulab.app.storage.impl.UserStorageDAOImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    UserStorageDAOImpl userStorageDAO;
    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Got user create request with: {}", userDto);
        if(userStorageDAO.getUserById(userDto.getId()) != null){
            throw new CreationFailedExeption("User with id " + userDto.getId() + " already exists");
        }
        return userStorageDAO.createUser(userDto);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        log.info("Got user update request with: {}", userDto);
        if(userStorageDAO.getUserById(userDto.getId()) == null){
            throw new UpdateFailedExeption("User with id " + userDto.getId() + " not found");
        }
        return userStorageDAO.updateUser(userDto);
    }

    @Override
    public UserDto getUserById(Long id) {
        log.info("Got user get request with: {}", id);
        UserDto userDto = userStorageDAO.getUserById(id);
        if(userDto == null){
            throw new NotFoundException("User with id " + id + " not found");
        }
        return userStorageDAO.getUserById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("Got user delete request with: {}", id);
        UserDto userDto = userStorageDAO.getUserById(id);
        if(userDto == null){
            throw new DeletionFailedExeption("User with id " + id + " not found");
        }
        userStorageDAO.deleteUserById(id);
    }
}
