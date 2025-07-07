package com.example.Healthcare.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "[User]") // SQL Server dùng [User] vì "User" là từ khóa
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
    // Getters và Setters
    
public void setUserId(String userId) {
    this.userId = userId;
}
    public String getUserId() {
        return userId;
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

public void setDOB(LocalDate dob) {
    this.dob = dob;
}

}