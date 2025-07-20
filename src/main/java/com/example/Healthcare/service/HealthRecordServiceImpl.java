package com.example.Healthcare.service;

import com.example.Healthcare.model.HealthRecord;
import com.example.Healthcare.repository.HealthRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthRecordServiceImpl implements HealthRecordService {

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    @Override
    public List<HealthRecord> getAllHealthRecords() {
        return healthRecordRepository.findAll();
    }

    @Override
    public HealthRecord getHealthRecordById(Long id) {
        return healthRecordRepository.findById(id).orElse(null);
    }

    @Override
    public HealthRecord createHealthRecord(HealthRecord healthRecord) {
        return healthRecordRepository.save(healthRecord);
    }

    @Override
    public HealthRecord updateHealthRecord(Long id, HealthRecord healthRecordDetails) {
        if (healthRecordRepository.existsById(id)) {
            healthRecordDetails.setHealthRecordId(id);
            return healthRecordRepository.save(healthRecordDetails);
        }
        return null;
    }

    @Override
    public void deleteHealthRecord(Long id) {
        healthRecordRepository.deleteById(id);
    }

    @Override
    public long countHealthRecords() {
        return healthRecordRepository.count(); // Sử dụng Repository riêng cho HealthRecord
    }
}