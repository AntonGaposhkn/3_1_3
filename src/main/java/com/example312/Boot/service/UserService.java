package com.example312.Boot.service;


import com.example312.Boot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List <User> getAllUsers();
    User getUserById (long id);
    void addUser(User user);
    void delete(long id);
    void updateUser (long id, User updateUser);;
}