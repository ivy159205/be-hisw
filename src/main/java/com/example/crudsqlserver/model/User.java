package com.example.crudsqlserver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "[User]") // SQL Server dùng [User] vì "User" là từ khóa
@Data // Lombok: Tự động tạo getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok: Tự động tạo constructor không tham số
@AllArgsConstructor // Lombok: Tự động tạo constructor có tất cả tham số
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password") // "password" là từ khóa → phải map rõ ràng
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "role")
    private String role;

    @Column(name = "dob")
    private LocalDate dob;

    // Quan hệ 1-n: Một User có nhiều DailyLog (nếu bạn cần dùng)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<DailyLog> dailyLogs;
}