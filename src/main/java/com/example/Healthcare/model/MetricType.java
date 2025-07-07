package com.example.Healthcare.model;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "MetricType") 
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

    // ✅ Getters/Setters đúng
    public String getMetricId() {
        return metricId;
    }

    public void setMetricId(String metricId) {
        this.metricId = metricId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
