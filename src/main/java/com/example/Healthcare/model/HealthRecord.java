package com.example.Healthcare.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "HealthRecord")
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SỬA Ở ĐÂY: Dùng IDENTITY
    @Column(name = "health_record_id")
    private Long healthRecordId;

    @Column(name = "[value]") // dùng [] vì 'value' là từ khóa
    private String value;

    @ManyToOne
    @JoinColumn(name = "log_id")
    @JsonBackReference
    private DailyLog dailyLog;

    @ManyToOne
    @JoinColumn(name = "metric_id")
    private MetricType metricType;

    // Getters and Setters

    public Long getHealthRecordId() {
        return healthRecordId;
    }

    public void setHealthRecordId(Long healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DailyLog getDailyLog() {
        return dailyLog;
    }

    public void setDailyLog(DailyLog dailyLog) {
        this.dailyLog = dailyLog;
    }

    public MetricType getMetricType() {
        return metricType;
    }

    public void setMetricType(MetricType metricType) {
        this.metricType = metricType;
    }
}
