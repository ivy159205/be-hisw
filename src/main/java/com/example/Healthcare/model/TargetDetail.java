package com.example.Healthcare.model;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "TargetDetail")
public class TargetDetail {

    @Id
    @Column(name = "detail_id")
    private String detailId;

    @Column(name = "comparison_type")
    private String comparisonType;

    @Column(name = "target_value")
    private Double targetValue;

    @Column(name = "aggregation_type")
    private String aggregationType;

    @ManyToOne
    @JoinColumn(name = "target_id")
    @JsonBackReference
    private Target target;

    @ManyToOne
    @JoinColumn(name = "metric_id")
    private MetricType metricType;

    // --- Getters & Setters ---

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getComparisonType() {
        return comparisonType;
    }

    public void setComparisonType(String comparisonType) {
        this.comparisonType = comparisonType;
    }

    public Double getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(Double targetValue) {
        this.targetValue = targetValue;
    }

    public String getAggregationType() {
        return aggregationType;
    }

    public void setAggregationType(String aggregationType) {
        this.aggregationType = aggregationType;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public MetricType getMetricType() {
        return metricType;
    }

    public void setMetricType(MetricType metricType) {
        this.metricType = metricType;
    }
}