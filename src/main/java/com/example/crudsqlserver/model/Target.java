package com.example.crudsqlserver.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "Target")
@Data // Lombok: Tự động tạo getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok: Tự động tạo constructor không tham số
@AllArgsConstructor // Lombok: Tự động tạo constructor có tất cả tham số
public class Target {
    @Id
    @Column(name = "target_id")
    private String targetId;

    @Column(name = "title")
    private String title;

    @Column(name = "[status]")
    private String status;

    @Column(name = "[start_date]")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "target")
    private List<TargetDetail> details;
}
