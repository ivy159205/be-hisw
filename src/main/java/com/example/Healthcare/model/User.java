package com.example.Healthcare.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "[User]") // SQL Server dùng [User] vì "User" là từ khóa
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Hoặc GenerationType.SEQUENCE nếu bạn dùng sequence của DB
    @Column(name = "user_id")
    private Long userId; // Thay đổi từ String sang Long

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

    // --- CONSTRUCTORS ---
    public User() {}

    // Constructor bỏ qua userId
    public User(String username, String password, String email, String phoneNumber, String gender, String role, LocalDate dob) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.role = role;
        this.dob = dob;
    }

    // Getters và Setters
    public Long getUserId() { // Trả về Long
        return userId;
    }

    public void setUserId(Long userId) { // Set Long
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) { // Đổi tên phương thức cho đúng chuẩn Java: setDob thay vì setDOB
        this.dob = dob;
    }

    public List<DailyLog> getDailyLogs() {
        return dailyLogs;
    }

    public void setDailyLogs(List<DailyLog> dailyLogs) {
        this.dailyLogs = dailyLogs;
    }
}