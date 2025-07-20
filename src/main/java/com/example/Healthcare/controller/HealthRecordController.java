package com.example.Healthcare.controller;

import com.example.Healthcare.model.HealthRecord;
import com.example.Healthcare.service.HealthRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/healthrecords")
@CrossOrigin(origins = "*") // Hoặc "http://127.0.0.1:5500" nếu bạn muốn cụ thể
public class HealthRecordController {

    @Autowired
    private HealthRecordService healthRecordService;

    @PostMapping
    public ResponseEntity<HealthRecord> createHealthRecord(@RequestBody HealthRecord healthRecord) {
        HealthRecord createdRecord = healthRecordService.createHealthRecord(healthRecord);
        return new ResponseEntity<>(createdRecord, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHealthRecord(@PathVariable Long id) {
        healthRecordService.deleteHealthRecord(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Thêm endpoint GET cho count:
    @GetMapping("/count")
    public long countHealthRecords() {
        return healthRecordService.countHealthRecords();
    }

    // Các endpoint khác nếu cần (ví dụ để hiển thị danh sách HealthRecord)
    @GetMapping
    public List<HealthRecord> getAllHealthRecords() {
        return healthRecordService.getAllHealthRecords();
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<HealthRecord> getHealthRecordById(@PathVariable Long id) {
    //     Optional<HealthRecord> record = healthRecordService.getHealthRecordById(id);
    //     return record.map(ResponseEntity::ok)
    //                  .orElseGet(() -> ResponseEntity.notFound().build());
    // }
}