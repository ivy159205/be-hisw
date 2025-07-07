package com.example.Healthcare.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "DailyLog")
public class DailyLog {
    @Id
    @Column(name = "log_id")
    private String logId;

    @Column(name = "log_date")
    private LocalDate logDate;

    @Column(name = "note")
    private String note;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

@OneToMany(mappedBy = "dailyLog", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonManagedReference
private List<HealthRecord> records;

 // Getters and Setters
    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDate logDate) {
        this.logDate = logDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<HealthRecord> getRecords() {
        return records;
    }

    public void setRecords(List<HealthRecord> records) {
        this.records = records;
    }
}