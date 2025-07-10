package com.example.Healthcare.service;

import com.example.Healthcare.model.User;
import com.example.Healthcare.DTO.RegisterRequest;
import com.example.Healthcare.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository userRepo;

    @Override
    public User register(RegisterRequest req) {
        if (userRepo.findByEmail(req.email).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setUserId(req.userId);
        user.setUsername(req.username);
        user.setPassword(req.password);
        user.setEmail(req.email);
        user.setPhoneNumber(req.phoneNumber);
        user.setGender(req.gender);
        user.setRole(req.role);
        user.setDOB(req.dob);

        return userRepo.save(user);
    }

    @Override
    public User login(String email, String password) {
        return userRepo.findByEmail(email)
                .filter(user -> user.getPassword().equals(password))
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
    }
}
