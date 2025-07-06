// src/main/java/com/example/crudsqlserver/service/UserService.java
package com.example.crudsqlserver.service;

import com.example.crudsqlserver.model.User;
import com.example.crudsqlserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> updateUser(String userId, User newUser) {
        return userRepository.findById(userId).map(existingUser -> {
            existingUser.setUsername(newUser.getUsername());
            existingUser.setPassword(newUser.getPassword());
            existingUser.setEmail(newUser.getEmail());
            existingUser.setPhoneNumber(newUser.getPhoneNumber());
            existingUser.setGender(newUser.getGender());
            existingUser.setDoB(newUser.getDoB());
            existingUser.setRole(newUser.getRole());
            return userRepository.save(existingUser);
        });
    }

    public boolean deleteUser(String userId) {
        return userRepository.findById(userId).map(existingUser -> {
            userRepository.delete(existingUser);
            return true;
        }).orElse(false);
    }
}