package com.example.Healthcare.DTO;

import com.example.Healthcare.model.User;

public class LoginResponse {
    public String message;
    public User user;

    public LoginResponse(String message, User user) {
        this.message = message;
        this.user = user;
    }
}