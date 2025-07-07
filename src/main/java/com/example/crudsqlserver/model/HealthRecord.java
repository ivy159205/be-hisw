package com.example.crudsqlserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HealthRecord")
@Data // Lombok: Tự động tạo getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok: Tự động tạo constructor không tham số
@AllArgsConstructor // Lombok: Tự động tạo constructor có tất cả tham số
public class HealthRecord {

    @Id
    @Column(name = "health_record_id")
    private String healthRecordId;

    @Column(name = "[value]") // dùng [] vì 'value' là từ khóa
    private Double value;

    @ManyToOne
    @JoinColumn(name = "log_id")
    @JsonBackReference
    private DailyLog dailyLog;

    @ManyToOne
    @JoinColumn(name = "metric_type_id")
    private MetricType metricType;
}
