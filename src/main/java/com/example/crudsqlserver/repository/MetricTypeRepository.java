package com.example.crudsqlserver.repository;
import com.example.crudsqlserver.model.MetricType;

import org.springframework.data.jpa.repository.JpaRepository;
public interface MetricTypeRepository extends JpaRepository<MetricType, String> {}