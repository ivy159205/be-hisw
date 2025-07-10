package com.example.Healthcare.controller;

import com.example.Healthcare.model.User;
import com.example.Healthcare.DTO.RegisterRequest;
import com.example.Healthcare.DTO.LoginRequest;
import com.example.Healthcare.DTO.LoginResponse;
import com.example.Healthcare.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private LoginService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest req) {
        User user = userService.register(req);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        User user = userService.login(req.email, req.password);
        return ResponseEntity.ok(new LoginResponse("Login successful", user));
    }
}
