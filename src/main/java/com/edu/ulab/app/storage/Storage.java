package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.exception.NotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class Storage {
    //todo создать хранилище в котором будут содержаться данные
    // сделать абстракции через которые можно будет производить операции с хранилищем
    // продумать логику поиска и сохранения
    // продумать возможные ошибки
    // учесть, что при сохранеии юзера или книги, должен генерироваться идентификатор
    // продумать что у узера может быть много книг и нужно создать эту связь
    // так же учесть, что методы хранилища принимают друго тип данных - учесть это в абстракции
     private static long BOOK_ID = 1;
     private static long USER_ID = 1;

     private Map<Long, User> users = new HashMap<>();
     private Map<Long, Book> books = new HashMap<>();

     public User getUserById(long id){
         return users.get(id);
     }

     public User createUser(User user){
        User userToSave = new User();

        userToSave.setId(USER_ID);
        userToSave.setFullName(user.getFullName());
        userToSave.setTitle(user.getTitle());
        userToSave.setAge(user.getAge());
        userToSave.setBooks(new ArrayList<>());

        users.put(USER_ID, userToSave);
        USER_ID++;

        return userToSave;
     }

    public User updateUser(User user){
        User userToUpdate = getUserById(user.getId());
        if(userToUpdate == null){
            throw new NotFoundException("Updating failed: User with id: " + user.getId() + " not found");
        }
        userToUpdate.setFullName(user.getFullName());
        userToUpdate.setTitle(user.getTitle());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setBooks(user.getBooks());
        userToUpdate.getBooks().stream().map(books::get).filter(Objects::nonNull).forEach(book -> book.setUserId(userToUpdate.getId()));

        users.put(userToUpdate.getId(), userToUpdate);

        return userToUpdate;
    }

    public void deleteUserById(long id) {
        User userToRemove = users.remove(id);
        if (userToRemove == null) {
            throw new NotFoundException("Deletion failed: user with id: " + id + " not found");
        } else {
            userToRemove.getBooks().stream().map(books::get).filter(Objects::nonNull).forEach(book -> book.setUserId(null));
        }
    }

    public Book getBookById(long id){
         return books.get(id);
    }

    public Book createBook(Book book){
         Book bookToSave = new Book();

         bookToSave.setId(BOOK_ID);
         bookToSave.setTitle(book.getTitle());
         bookToSave.setAuthor(book.getAuthor());
         bookToSave.setPageCount(book.getPageCount());

         books.put(BOOK_ID, bookToSave);

         BOOK_ID++;

         return bookToSave;
    }

    public Book updateBook(Book book){
         Book bookToUpdate = books.get(book.getId());
         if(bookToUpdate == null){
             throw new NotFoundException("Book with id: " + book.getId() + " not found");
         }
         bookToUpdate.setTitle(book.getTitle());
         bookToUpdate.setAuthor(book.getAuthor());
         bookToUpdate.setPageCount(book.getPageCount());

         if(book.getUserId() == null){
             books.put(bookToUpdate.getId(), bookToUpdate);
             return bookToUpdate;
         }

         bookToUpdate.setUserId(book.getUserId());

         User userToUpdate = users.get(book.getUserId());
         if(userToUpdate == null){
             throw new NotFoundException("Trying to assign a book to an existing user with id: " + book.getUserId());
         }

         userToUpdate.addBook(bookToUpdate);
         users.put(userToUpdate.getId(), userToUpdate);
         books.put(bookToUpdate.getId(), bookToUpdate);

         return bookToUpdate;
    }

    public void deleteBookById(long id){
         Book bookToDelete = books.remove(id);

         if(bookToDelete == null) {
             throw new NotFoundException("Deletion failed: book with id: " + id + " not found");
         } else {
             users.get(bookToDelete.getUserId()).deleteBook(bookToDelete); //test
         }

    }

}
