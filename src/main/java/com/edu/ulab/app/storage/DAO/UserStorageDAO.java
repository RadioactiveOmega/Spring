package com.edu.ulab.app.storage.DAO;
import com.edu.ulab.app.dto.UserDto;

public interface UserStorageDAO {
    UserDto getUserById(long id);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    void deleteUserById(long id);

}
