package com.example.Healthcare.service;

import com.example.Healthcare.DTO.HealthRecordDTO; // THÊM: Import DTO
import com.example.Healthcare.model.HealthRecord;
import com.example.Healthcare.repository.HealthRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // THÊM: Import Transactional

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HealthRecordServiceImpl implements HealthRecordService {

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    // THÊM: Hàm helper để chuyển đổi từ Entity sang DTO
    private HealthRecordDTO convertToDto(HealthRecord record) {
        if (record == null) {
            return null;
        }
        HealthRecordDTO dto = new HealthRecordDTO();
        dto.setHealthRecordId(record.getHealthRecordId());
        dto.setValue(record.getValue());

        // Lấy thông tin từ các quan hệ một cách an toàn
        if (record.getDailyLog() != null) {
            dto.setLogId(record.getDailyLog().getLogId());
            dto.setLogDate(record.getDailyLog().getLogDate());
        }

        if (record.getMetricType() != null) {
            dto.setMetricId(record.getMetricType().getMetricId());
            dto.setMetricName(record.getMetricType().getName());
            dto.setUnit(record.getMetricType().getUnit());
        }
        return dto;
    }

    @Override
    @Transactional(readOnly = true) // SỬA: Thêm transactional và trả về DTO list
    public List<HealthRecordDTO> getAllHealthRecordsAsDTO() {
        return healthRecordRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true) // SỬA: Thêm transactional và trả về DTO
    public HealthRecordDTO getHealthRecordByIdAsDTO(Long id) {
        return healthRecordRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Override
    @Transactional // SỬA: Thêm transactional và trả về DTO
    public HealthRecordDTO createHealthRecordAsDTO(HealthRecord healthRecord) {
        HealthRecord savedRecord = healthRecordRepository.save(healthRecord);
        return convertToDto(savedRecord);
    }

    @Override
    @Transactional // SỬA: Thêm transactional và trả về DTO
    public HealthRecordDTO updateHealthRecordAsDTO(Long id, HealthRecord healthRecordDetails) {
        return healthRecordRepository.findById(id)
            .map(existingRecord -> {
                // Chỉ cập nhật các trường cần thiết từ đối tượng được gửi lên
                existingRecord.setDailyLog(healthRecordDetails.getDailyLog());
                existingRecord.setMetricType(healthRecordDetails.getMetricType());
                existingRecord.setValue(healthRecordDetails.getValue());
                
                HealthRecord updatedRecord = healthRecordRepository.save(existingRecord);
                return convertToDto(updatedRecord);
            })
            .orElse(null); // Trả về null nếu không tìm thấy record với id tương ứng
    }

    @Override
    public void deleteHealthRecord(Long id) {
        healthRecordRepository.deleteById(id);
    }

    @Override
    public long countHealthRecords() {
        return healthRecordRepository.count();
    }
}