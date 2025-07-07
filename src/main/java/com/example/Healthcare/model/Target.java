package com.example.Healthcare.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
public class Target {
    @Id
    private String targetId;
    private String title;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "target")
    private List<TargetDetail> details;
    // Getters/Setters
public void setTarrgetId(String targetId) {
    this.targetId = targetId;
}
        public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}
        public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
    public LocalDate getStartDate() {
    return startDate;
}

public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
}
    public LocalDate getEndDate() {
    return endDate;
}

public void setDOB(LocalDate endDate) {
    this.endDate = endDate;
}

}
