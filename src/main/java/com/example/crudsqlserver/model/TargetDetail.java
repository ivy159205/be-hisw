package com.example.crudsqlserver.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TargetDetail")
@Data // Lombok: Tự động tạo getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok: Tự động tạo constructor không tham số
@AllArgsConstructor // Lombok: Tự động tạo constructor có tất cả tham số
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
    private Target target;

    @ManyToOne
    @JoinColumn(name = "metric_id")
    private MetricType metricType;
}
