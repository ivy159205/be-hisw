package com.example.Healthcare.service;

import com.example.Healthcare.model.HealthRecord;
import java.util.List;

public interface HealthRecordService {
    List<HealthRecord> getAllHealthRecords();
    HealthRecord getHealthRecordById(Long id);
    HealthRecord createHealthRecord(HealthRecord healthRecord);
    HealthRecord updateHealthRecord(Long id, HealthRecord healthRecord);
    void deleteHealthRecord(Long id);
    long countHealthRecords(); // Thêm phương thức này
}