// HealthRecordServiceImpl.java
package com.example.Healthcare.service;

import com.example.Healthcare.DTO.HealthRecordDTO;
import com.example.Healthcare.DTO.HealthRecordCreateDTO;
import com.example.Healthcare.DTO.MetricDTO;
import com.example.Healthcare.model.*;
import com.example.Healthcare.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HealthRecordServiceImpl implements HealthRecordService {

    @Autowired
    private HealthRecordRepository healthRecordRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MetricTypeRepository metricTypeRepository;
    @Autowired
    private DailyLogRepository dailyLogRepository;

    private HealthRecordDTO convertToDto(HealthRecord record) {
        if (record == null)
            return null;
        HealthRecordDTO dto = new HealthRecordDTO();
        dto.setHealthRecordId(record.getHealthRecordId());
        dto.setValue(record.getValue());

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
    @Transactional(readOnly = true)
    public List<HealthRecordDTO> getAllHealthRecordsAsDTO() {
        return healthRecordRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<HealthRecordDTO> getHealthRecordsByUserId(Long userId) {
        return healthRecordRepository.findByDailyLog_User_UserId(userId)
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<HealthRecordDTO> getHealthRecordsByUserAndDate(Long userId, LocalDate date) {
        return healthRecordRepository.findByDailyLog_User_UserIdAndDailyLog_LogDate(userId, date)
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public HealthRecordDTO getHealthRecordByIdAsDTO(Long id) {
        return healthRecordRepository.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    @Transactional
    public HealthRecordDTO createHealthRecordAsDTO(HealthRecord healthRecord) {
        HealthRecord saved = healthRecordRepository.save(healthRecord);
        return convertToDto(saved);
    }

    @Override
    @Transactional
    public HealthRecordDTO updateHealthRecordAsDTO(Long id, HealthRecord healthRecordDetails) {
        return healthRecordRepository.findById(id).map(existing -> {
            existing.setDailyLog(healthRecordDetails.getDailyLog());
            existing.setMetricType(healthRecordDetails.getMetricType());
            existing.setValue(healthRecordDetails.getValue());
            return convertToDto(healthRecordRepository.save(existing));
        }).orElse(null);
    }

    @Override
    @Transactional
    public HealthRecordDTO createHealthRecordFromDTO(HealthRecordCreateDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        LocalDate date = LocalDate.parse(dto.getDate());

        DailyLog dailyLog = dailyLogRepository.findByUserAndLogDate(user, date)
                .orElseGet(() -> {
                    DailyLog log = new DailyLog();
                    log.setUser(user);
                    log.setLogDate(date);
                    return dailyLogRepository.save(log);
                });

        // ✅ Cập nhật note
        dailyLog.setNote(dto.getNotes());
        dailyLogRepository.save(dailyLog); // Lưu thay đổi

        List<HealthRecord> records = new ArrayList<>();
        for (MetricDTO m : dto.getMetrics()) {
            MetricType metric = metricTypeRepository.findById((long) m.getMetricId()).orElseThrow();
            HealthRecord r = new HealthRecord();
            r.setDailyLog(dailyLog);
            r.setMetricType(metric);
            r.setValue(m.getValue());
            records.add(healthRecordRepository.save(r));
        }

        return records.isEmpty() ? null : convertToDto(records.get(0));
    }

    @Override
    public void deleteHealthRecord(Long id) {
        healthRecordRepository.deleteById(id);
    }

    @Override
    public long countHealthRecords() {
        return healthRecordRepository.count();
    }

    @Override
    @Transactional
    public List<HealthRecordDTO> updateHealthRecordsFromDTO(HealthRecordCreateDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow();
        LocalDate date = LocalDate.parse(dto.getDate());

        // Tìm hoặc tạo DailyLog
        DailyLog dailyLog = dailyLogRepository.findByUserAndLogDate(user, date)
                .orElseGet(() -> {
                    DailyLog log = new DailyLog();
                    log.setUser(user);
                    log.setLogDate(date);
                    return dailyLogRepository.save(log);
                });

        // ✅ Cập nhật note nếu có trong DTO
        dailyLog.setNote(dto.getNotes());
        dailyLogRepository.save(dailyLog); // Đảm bảo thay đổi được lưu

        List<HealthRecordDTO> updatedDtos = new ArrayList<>();

        for (MetricDTO metricDTO : dto.getMetrics()) {
            Long metricId = (long) metricDTO.getMetricId();
            MetricType metricType = metricTypeRepository.findById(metricId).orElseThrow();

            // Tìm bản ghi health record hiện tại
            HealthRecord record = healthRecordRepository
                    .findByDailyLog_User_UserIdAndDailyLog_LogDateAndMetricType_MetricId(
                            user.getUserId(), date, metricId)
                    .orElse(null);

            if (record != null) {
                record.setValue(metricDTO.getValue());
                record.setDailyLog(dailyLog);
                record.setMetricType(metricType);
                updatedDtos.add(convertToDto(healthRecordRepository.save(record)));
            }
        }

        return updatedDtos;
    }

}
