package com.example.crudsqlserver.service;

import com.example.crudsqlserver.model.MetricType;
import com.example.crudsqlserver.repository.MetricTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricTypeServiceImpl implements MetricTypeService {

    @Autowired
    private MetricTypeRepository metricRepo;

    @Override
    public List<MetricType> getAll() {
        return metricRepo.findAll();
    }

    @Override
    public MetricType getById(String id) {
        return metricRepo.findById(id).orElse(null);
    }

    @Override
    public MetricType create(MetricType metricType) {
        return metricRepo.save(metricType);
    }

    @Override
    public MetricType update(String id, MetricType metricType) {
        if (metricRepo.existsById(id)) {
            metricType.setMetricId(id);
            return metricRepo.save(metricType);
        }
        return null;
    }

    @Override
    public void delete(String id) {
        metricRepo.deleteById(id);
    }
}