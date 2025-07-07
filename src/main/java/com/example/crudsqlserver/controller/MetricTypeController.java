package com.example.crudsqlserver.controller;


import com.example.crudsqlserver.model.MetricType;
import com.example.crudsqlserver.service.MetricTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metrictypes")
public class MetricTypeController {

    @Autowired
    private MetricTypeService metricService;

    @GetMapping
    public List<MetricType> getAllMetrics() {
        return metricService.getAll();
    }

    @GetMapping("/{id}")
    public MetricType getMetricById(@PathVariable String id) {
        return metricService.getById(id);
    }

    @PostMapping
    public MetricType createMetric(@RequestBody MetricType metricType) {
        return metricService.create(metricType);
    }

    @PutMapping("/{id}")
    public MetricType updateMetric(@PathVariable String id, @RequestBody MetricType metricType) {
        return metricService.update(id, metricType);
    }

    @DeleteMapping("/{id}")
    public void deleteMetric(@PathVariable String id) {
        metricService.delete(id);
    }
}