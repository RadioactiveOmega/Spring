package com.edu.ulab.app.storage.DAO;

import com.edu.ulab.app.dto.BookDto;
import java.util.List;

public interface BookStorageDAO {
    BookDto getBookById(long id);

    BookDto createBook(BookDto bookDto);

    BookDto updateBook(BookDto bookDto);

    void deleteBookById(long id);

    List<BookDto> getAllBooks();
}
