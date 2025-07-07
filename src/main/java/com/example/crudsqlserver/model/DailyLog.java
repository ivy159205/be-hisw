// src/main/java/com/example/crudsqlserver/model/User.java
package com.example.crudsqlserver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "DailyLog")
@Data // Lombok: Tự động tạo getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok: Tự động tạo constructor không tham số
@AllArgsConstructor // Lombok: Tự động tạo constructor có tất cả tham số
public class DailyLog {
    @Id
    @Column(name = "log_id")
    private String logId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "log_date")
    private LocalDate logDate;

    @Column(name = "note")
    private String note;

    @OneToMany(mappedBy = "dailyLog", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<HealthRecord> records;
    
    public List<HealthRecord> getRecords() {
        return records;
    }

    public void setRecords(List<HealthRecord> records) {
        this.records = records;
    }
}