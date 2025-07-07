package com.example.Healthcare.service;

import com.example.Healthcare.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(String id);
    User createUser(User user);
    User updateUser(String id, User user);
    void deleteUser(String id);
}
