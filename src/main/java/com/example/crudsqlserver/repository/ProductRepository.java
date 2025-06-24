// src/main/java/com/example/crudsqlserver/repository/ProductRepository.java
package com.example.crudsqlserver.repository;

import com.example.crudsqlserver.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository đã cung cấp sẵn các phương thức CRUD cơ bản:
    // save(), findById(), findAll(), deleteById(), ...
}