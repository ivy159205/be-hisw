package com.example.Healthcare.controller;

import com.example.Healthcare.DTO.LoginRequest;
import com.example.Healthcare.DTO.LoginResponse;
import com.example.Healthcare.DTO.RegisterRequest;
import com.example.Healthcare.model.User;
import com.example.Healthcare.security.JwtUtil;
import com.example.Healthcare.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Healthcare.DTO.ResetPasswordRequest;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Cho phép gọi từ Flutter
public class AuthController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User newUser = loginService.register(request);
            return ResponseEntity.ok("User registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            User user = loginService.login(request.email, request.password);
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

            LoginResponse response = new LoginResponse("Login successful", token);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            loginService.resetPassword(request.getEmail(), request.getNewPassword());
            return ResponseEntity.ok("Password updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
