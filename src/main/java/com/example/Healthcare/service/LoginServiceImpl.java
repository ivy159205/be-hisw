package com.example.Healthcare.service;

import com.example.Healthcare.model.User;
import com.example.Healthcare.DTO.RegisterRequest;
import com.example.Healthcare.repository.LoginRepository;
import com.example.Healthcare.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User register(RegisterRequest req) {
        if (userRepo.findByEmail(req.email).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setUsername(req.username);
        user.setPassword(passwordEncoder.encode(req.password)); // Mã hoá mật khẩu
        user.setEmail(req.email);
        user.setPhoneNumber(req.phoneNumber);
        user.setGender(req.gender);
        user.setRole(req.role == null ? "user" : req.role); // Gán mặc định nếu thiếu
        user.setDob(req.dob);

        return userRepo.save(user);
    }

    @Override
    public User login(String email, String password) {
        return userRepo.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
    }

    public String generateTokenForUser(User user) {
        return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }

    @Override
    public void resetPassword(String email, String newPassword) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
    }

}
