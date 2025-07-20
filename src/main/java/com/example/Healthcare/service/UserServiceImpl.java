package com.example.Healthcare.service;

import com.example.Healthcare.model.User;
import com.example.Healthcare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) { // Thay String bằng Long
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) {
        // ID sẽ được SQL Server tự động tạo khi persist
        return userRepo.save(user);
    }

    @Override
    public User updateUser(Long id, User userDetails) { // Thay String bằng Long
        User existingUser = userRepo.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(userDetails.getUsername());
            existingUser.setPassword(userDetails.getPassword());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPhoneNumber(userDetails.getPhoneNumber());
            existingUser.setGender(userDetails.getGender());
            existingUser.setDob(userDetails.getDob());
            existingUser.setRole(userDetails.getRole());
            return userRepo.save(existingUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) { // Thay String bằng Long
        userRepo.deleteById(id);
    }

    @Override
    public long countUsers() {
        return userRepo.count();
    }
}