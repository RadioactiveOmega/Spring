package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.exception.CreationFailedExeption;
import com.edu.ulab.app.exception.DeletionFailedExeption;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.exception.UpdateFailedExeption;
import com.edu.ulab.app.service.BookService;
import com.edu.ulab.app.storage.impl.BookStorageDAOImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    BookStorageDAOImpl bookStorageDAO;
    @Override
    public BookDto createBook(BookDto bookDto) {
        log.info("Got book create request with: {}", bookDto);
        if(bookStorageDAO.getBookById(bookDto.getId()) != null){
            throw new CreationFailedExeption("Book with id " + bookDto.getId() +" already exists");
        }

        return bookStorageDAO.createBook(bookDto);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        log.info("Got book update request with: {}", bookDto);
        if(bookStorageDAO.getBookById(bookDto.getId()) == null){
            throw new UpdateFailedExeption("Book with id " + bookDto.getId() +" not found");
        }
        return bookStorageDAO.updateBook(bookDto);
    }

    @Override
    public BookDto getBookById(Long id) {
        log.info("Got book get request with: {}", id);
        BookDto bookDto = bookStorageDAO.getBookById(id);
        if(bookDto == null){
            throw new NotFoundException("Book with id " + id +" not found");
        }
        return bookDto;
    }

    @Override
    public List<BookDto> getBooksByUserId(Long id) {
        log.info("Got a request for all books with user id: {}", id);
        return bookStorageDAO.getAllBooks()
                .stream()
                .filter(Objects::nonNull)
                .filter(bookDto -> bookDto.getUserId().equals(id)).
                toList();
    }

    @Override
    public void deleteBookById(Long id) {
        log.info("Got book delete request with: {}", id);
        BookDto bookDto = bookStorageDAO.getBookById(id);
        if(bookDto == null){
            throw new DeletionFailedExeption("Book with id " + id +" not found");
        }
        bookStorageDAO.deleteBookById(id);
    }
}
