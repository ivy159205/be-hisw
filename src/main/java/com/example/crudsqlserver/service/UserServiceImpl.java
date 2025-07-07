package com.example.crudsqlserver.service;

import com.example.crudsqlserver.model.User;
import com.example.crudsqlserver.repository.UserRepository;
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
    public User getUserById(String id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User updateUser(String id, User user) {
        if (userRepo.existsById(id)) {
            user.setUserId(id);
            return userRepo.save(user);
        }
        return null;
    }

    @Override
    public void deleteUser(String id) {
        userRepo.deleteById(id);
    }
}
