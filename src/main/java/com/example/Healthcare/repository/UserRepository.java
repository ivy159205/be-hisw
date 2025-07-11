package com.example.Healthcare.repository;

import com.example.Healthcare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {  
    User findByUsername(String username);
}