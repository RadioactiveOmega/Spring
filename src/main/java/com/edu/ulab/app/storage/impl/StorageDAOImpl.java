package com.edu.ulab.app.storage.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.mapper.DtoMappers.BookDtoMapper;
import com.edu.ulab.app.mapper.DtoMappers.UserDtoMapper;
import com.edu.ulab.app.storage.Storage;
import com.edu.ulab.app.storage.StorageDAO;

import java.util.List;
import java.util.Objects;

public class StorageDAOImpl implements StorageDAO {

    Storage storage;
    UserDtoMapper userDtoMapper;
    BookDtoMapper bookDtoMapper;

    @Override
    public UserDto getUserById(long id) {
        User user = storage.getUserById(id);

        if(user == null){
            throw new NotFoundException("User with id: " + id + " not found!");
        }
        return userDtoMapper.userToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = storage.createUser(userDtoMapper.userDtoToUser(userDto));
        return userDtoMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = storage.updateUser(userDtoMapper.userDtoToUser(userDto));
        return userDtoMapper.userToUserDto(user);
    }

    @Override
    public void deleteUserById(long id) {
        storage.deleteUserById(id);
    }

    @Override
    public List<BookDto> getUserBooksByUserId(long id) {
        User user = storage.getUserById(id);
        if(user == null){
            throw new NotFoundException("User with id: " + id + " not found!");
        }

       return user.getBooks()
               .stream()
               .filter(Objects::nonNull)
               .map(storage::getBookById)
               .map(bookDtoMapper::bookToBookDto)
               .toList();
    }

    @Override
    public BookDto getBookById(long id) {
        Book book = storage.getBookById(id);
        if(book == null){
            throw new NotFoundException("Book with id: " + id + " not found!");
        }
        return bookDtoMapper.bookToBookDto(book);
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = storage.createBook(bookDtoMapper.bookDtoToBook(bookDto));
        return bookDtoMapper.bookToBookDto(book);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        Book book = storage.updateBook(bookDtoMapper.bookDtoToBook(bookDto));
        return bookDtoMapper.bookToBookDto(book);
    }

    @Override
    public void deleteBookById(long id) {
        storage.deleteBookById(id);
    }
}
