package com.example.Healthcare.model;
import jakarta.persistence.*;

@Entity
public class TargetDetail {
    @Id
    private String detailId;
    private String comparisonType;
    private Double targetValue;
    private String aggregationType;

    @ManyToOne
    @JoinColumn(name = "target_id")
    private Target target;

    @ManyToOne
    @JoinColumn(name = "metric_id")
    private MetricType metricType;
    // Getters/Setters
    public void setDetailId(String detailId) {
    this.detailId = detailId;
}
    public String getComType() {
    return comparisonType;
}

public void setComType(String comparisonType) {
    this.comparisonType = comparisonType;
}
    public String getAggType() {
    return aggregationType;
}

public void setAggType(String aggregationType) {
    this.aggregationType = aggregationType;
}
        public Double getTarValue() {
    return targetValue;
}

public void setTarValue(Double targetValue) {
    this.targetValue = targetValue;
}
}
