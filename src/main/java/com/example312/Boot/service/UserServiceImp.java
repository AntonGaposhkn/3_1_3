package com.example312.Boot.service;

import com.example312.Boot.dao.UserDAO;
import com.example312.Boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void addUser(User user, String role) {
        userDAO.addUser(user, role);
    }

    @Override
    public void delete(long id) {
        userDAO.delete(id);
    }

    @Override
    public void updateUser (long id, User updateUser, String role) {
        userDAO.updateUser(id, updateUser, role);
    }
}