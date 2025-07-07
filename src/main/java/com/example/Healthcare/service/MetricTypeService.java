package com.example.Healthcare.service;

import com.example.Healthcare.model.MetricType;

import java.util.List;

public interface MetricTypeService {
    List<MetricType> getAll();
    MetricType getById(String id);
    MetricType create(MetricType metricType);
    MetricType update(String id, MetricType metricType);
    void delete(String id);
}