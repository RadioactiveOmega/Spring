package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.User;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserStorage {
    //todo создать хранилище в котором будут содержаться данные
    // сделать абстракции через которые можно будет производить операции с хранилищем
    // продумать логику поиска и сохранения
    // продумать возможные ошибки
    // учесть, что при сохранеии юзера или книги, должен генерироваться идентификатор
    // продумать что у узера может быть много книг и нужно создать эту связь
    // так же учесть, что методы хранилища принимают друго тип данных - учесть это в абстракции
     private Map<Long, User> users = new HashMap<>();

     public User getUserById(long id){
         return users.get(id);
     }

     public User createUser(User user){
        users.put(user.getId(), user);
        return user;
     }

    public User updateUser(User user){
        users.put(user.getId(), user);
        return user;
    }

    public void deleteUserById(long id) {
        users.remove(id);
    }
}
