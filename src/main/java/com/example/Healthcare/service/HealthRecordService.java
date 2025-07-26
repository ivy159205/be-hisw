package com.example.Healthcare.service;

import com.example.Healthcare.DTO.HealthRecordDTO; // SỬA: Import DTO
import com.example.Healthcare.model.HealthRecord;
import java.util.List;

public interface HealthRecordService {

    // Các phương thức trả về DTO cho Controller
    List<HealthRecordDTO> getAllHealthRecordsAsDTO();
    HealthRecordDTO getHealthRecordByIdAsDTO(Long id);
    HealthRecordDTO createHealthRecordAsDTO(HealthRecord healthRecord);
    HealthRecordDTO updateHealthRecordAsDTO(Long id, HealthRecord healthRecordDetails);
    List<HealthRecordDTO> getHealthRecordsByUserId(Long userId);

    // Các phương thức nội bộ hoặc không trả về cho API có thể giữ nguyên
    void deleteHealthRecord(Long id);
    long countHealthRecords();
}