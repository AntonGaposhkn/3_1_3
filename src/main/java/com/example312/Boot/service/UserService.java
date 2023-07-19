package com.example312.Boot.service;


import com.example312.Boot.model.User;

import java.util.List;

public interface UserService {

    List <User> getAllUsers();
    User getUserById (long id);
    void addUser(User user, String role);
    void delete(long id);
    void updateUser (long id, User updateUser, String role);
}