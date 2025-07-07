package com.example.crudsqlserver.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "MetricType") 
@Data // Lombok: Tự động tạo getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok: Tự động tạo constructor không tham số
@AllArgsConstructor // Lombok: Tự động tạo constructor có tất cả tham số
public class MetricType {
    @Id
    @Column(name = "metric_id")
    private String metricId;
     
    @Column(name = "[name]")
    private String name;

    @Column(name = "unit")
    private String unit;

    @OneToMany(mappedBy = "metricType")
    private List<HealthRecord> records;

    @OneToMany(mappedBy = "metricType")
    private List<TargetDetail> targetDetails;
}
