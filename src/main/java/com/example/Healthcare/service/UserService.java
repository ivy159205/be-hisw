package com.example.Healthcare.service;

import com.example.Healthcare.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id); // Thay String bằng Long
    User createUser(User user);
    User updateUser(Long id, User userDetails); // Thay String bằng Long
    void deleteUser(Long id); // Thay String bằng Long
    long countUsers();
}