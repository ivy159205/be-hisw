package com.example.Healthcare.controller;

import com.example.Healthcare.DTO.HealthRecordDTO; // SỬA: Import DTO
import com.example.Healthcare.model.HealthRecord;
import com.example.Healthcare.service.HealthRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/healthrecords")
@CrossOrigin(origins = "*")
public class HealthRecordController {

    @Autowired
    private HealthRecordService healthRecordService;

    @GetMapping
    // SỬA: Trả về List<HealthRecordDTO>
    public List<HealthRecordDTO> getAllHealthRecords() {
        return healthRecordService.getAllHealthRecordsAsDTO();
    }

    @GetMapping("/{id}")
    // SỬA: Trả về ResponseEntity<HealthRecordDTO> và gọi đúng service
    public ResponseEntity<HealthRecordDTO> getHealthRecordById(@PathVariable Long id) {
        HealthRecordDTO dto = healthRecordService.getHealthRecordByIdAsDTO(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/user/{userId}")
public ResponseEntity<List<HealthRecordDTO>> getHealthRecordsByUserId(@PathVariable Long userId) {
    List<HealthRecordDTO> records = healthRecordService.getHealthRecordsByUserId(userId);
    return ResponseEntity.ok(records);
}
    @PostMapping
    // SỬA: Trả về ResponseEntity<HealthRecordDTO> và gọi đúng service
    public ResponseEntity<HealthRecordDTO> createHealthRecord(@RequestBody HealthRecord healthRecord) {
        HealthRecordDTO createdDto = healthRecordService.createHealthRecordAsDTO(healthRecord);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }
    
    // THÊM: Endpoint PUT để cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<HealthRecordDTO> updateHealthRecord(@PathVariable Long id, @RequestBody HealthRecord healthRecordDetails) {
        HealthRecordDTO updatedDto = healthRecordService.updateHealthRecordAsDTO(id, healthRecordDetails);
        if (updatedDto != null) {
            return ResponseEntity.ok(updatedDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHealthRecord(@PathVariable Long id) {
        healthRecordService.deleteHealthRecord(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public long countHealthRecords() {
        return healthRecordService.countHealthRecords();
    }
}