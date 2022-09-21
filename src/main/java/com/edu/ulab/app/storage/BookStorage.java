package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookStorage {
    private Map<Long, Book> books = new HashMap<>();

    public Book getBookById(long id){
        return books.get(id);
    }

    public Book createBook(Book book){
        books.put(book.getId(), book);
        return book;
    }

    public Book updateBook(Book book){
        books.put(book.getId(), book);
        return book;
    }

    public void deleteBookById(long id){
       books.remove(id);
    }

    public List<Book> getBooks(){
        return new ArrayList<>(books.values());
    }
}
