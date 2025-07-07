package com.example.crudsqlserver.repository;

import com.example.crudsqlserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {  
    User findByUsername(String username);
}