package com.edu.ulab.app.storage.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.mapper.DtoMappers.BookDtoMapper;
import com.edu.ulab.app.storage.BookStorage;
import com.edu.ulab.app.storage.DAO.BookStorageDAO;

import java.util.List;

public class BookStorageDAOImpl implements BookStorageDAO {
    BookDtoMapper bookDtoMapper;

    BookStorage bookStorage;

    @Override
    public BookDto getBookById(long id) {
        Book book = bookStorage.getBookById(id);
        return bookDtoMapper.bookToBookDto(book);
    }
    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = bookStorage.createBook(bookDtoMapper.bookDtoToBook(bookDto));
        return bookDtoMapper.bookToBookDto(book);
    }
    @Override
    public BookDto updateBook(BookDto bookDto) {
        Book book = bookStorage.updateBook(bookDtoMapper.bookDtoToBook(bookDto));
        return bookDtoMapper.bookToBookDto(book);
    }
    @Override
    public void deleteBookById(long id) {
        bookStorage.deleteBookById(id);
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> bookList = bookStorage.getBooks();
        return bookList
                .stream()
                .map(bookDtoMapper::bookToBookDto)
                .toList();
    }
}