package com.example.crudsqlserver.service;

import com.example.crudsqlserver.model.MetricType;

import java.util.List;

public interface MetricTypeService {
    List<MetricType> getAll();
    MetricType getById(String id);
    MetricType create(MetricType metricType);
    MetricType update(String id, MetricType metricType);
    void delete(String id);
}