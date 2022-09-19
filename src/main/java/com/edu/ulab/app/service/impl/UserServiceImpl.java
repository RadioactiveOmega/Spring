package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.service.UserService;
import com.edu.ulab.app.storage.impl.StorageDAOImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    StorageDAOImpl storageDAOImpl;
    @Override
    public UserDto createUser(UserDto userDto) {
        return storageDAOImpl.createUser(userDto);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return storageDAOImpl.updateUser(userDto);
    }

    @Override
    public UserDto getUserById(Long id) {
        return storageDAOImpl.getUserById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        storageDAOImpl.deleteUserById(id);
    }
}
