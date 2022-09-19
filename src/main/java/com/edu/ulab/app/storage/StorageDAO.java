package com.edu.ulab.app.storage;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.dto.UserDto;

import java.util.List;

public interface StorageDAO {
    UserDto getUserById(long id);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    void deleteUserById(long id);

    List<BookDto> getUserBooksByUserId(long id);

    BookDto getBookById(long id);

    BookDto createBook(BookDto bookDto);

    BookDto updateBook(BookDto bookDto);

    void deleteBookById(long id);
}
