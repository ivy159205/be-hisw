// src/main/java/com/example/crudsqlserver/repository/UserRepository.java
package com.example.crudsqlserver.repository;

import com.example.crudsqlserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // JpaRepository đã cung cấp sẵn các phương thức CRUD cơ bản:
    // save(), findById(), findAll(), deleteById(), ...
}