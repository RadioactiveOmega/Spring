package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.service.BookService;
import com.edu.ulab.app.storage.impl.StorageDAOImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    StorageDAOImpl storageDAOImpl;
    @Override
    public BookDto createBook(BookDto bookDto) {
        return storageDAOImpl.createBook(bookDto);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        return storageDAOImpl.updateBook(bookDto);
    }

    @Override
    public BookDto getBookById(Long id) {
        return storageDAOImpl.getBookById(id);
    }

    @Override
    public List<BookDto> getBooksByUserId(Long id) {
        return storageDAOImpl.getUserBooksByUserId(id);
    }

    @Override
    public void deleteBookById(Long id) {
        storageDAOImpl.deleteBookById(id);
    }
}
