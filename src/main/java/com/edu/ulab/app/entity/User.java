package com.edu.ulab.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String fullName;
    private String title;
    private Integer age;
    private List<Long> books;

    public void addBook(Book book){
        books.add(book.getId());
    }
    public void deleteBook(Book book){
        books.remove(book.getId());
    }
}
