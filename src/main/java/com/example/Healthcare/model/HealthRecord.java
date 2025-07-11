package com.example.Healthcare.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "HealthRecord")
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

    // Getters and Setters

    public String getHealthRecordId() {
        return healthRecordId;
    }

    public void setHealthRecordId(String healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
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
