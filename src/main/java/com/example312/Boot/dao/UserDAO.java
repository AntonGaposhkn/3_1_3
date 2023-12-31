package com.example312.Boot.dao;


import com.example312.Boot.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();
    User getUserById (long id);
    User getUserByName(String name);

    void addUser(User user, String role);

    void delete(long id);
    void updateUser (long id, User updateUser, String role);
}
