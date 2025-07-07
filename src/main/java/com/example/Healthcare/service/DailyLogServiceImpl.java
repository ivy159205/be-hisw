package com.example.Healthcare.service;

import com.example.Healthcare.model.DailyLog;
import com.example.Healthcare.model.HealthRecord;
import com.example.Healthcare.model.User;
import com.example.Healthcare.repository.DailyLogRepository;
import com.example.Healthcare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class DailyLogServiceImpl implements DailyLogService {

    @Autowired
    private DailyLogRepository dailyLogRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<DailyLog> getAllLogs() {
        return dailyLogRepo.findAll();
    }

    @Override
    public DailyLog getLogById(String id) {
        return dailyLogRepo.findById(id).orElse(null);
    }

    @Override
    public List<DailyLog> getLogsByUserId(String userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            return dailyLogRepo.findByUser(user);
        }
        return List.of();
    }

    @Override
    public List<DailyLog> getLogsByUserIdAndDateRange(String userId, LocalDate start, LocalDate end) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            return dailyLogRepo.findByUserAndLogDateBetween(user, start, end);
        }
        return List.of();
    }

    @Override
    public DailyLog createLog(DailyLog log) {
        // Gán log cho từng record nếu có
        if (log.getRecords() != null && !log.getRecords().isEmpty()) {
            for (var record : log.getRecords()) {
                // Gán ngược DailyLog cho HealthRecord
                record.setDailyLog(log);

                // Nếu chưa có ID thì tạo tự động
                if (record.getHealthRecordId() == null || record.getHealthRecordId().isEmpty()) {
                    record.setHealthRecordId(java.util.UUID.randomUUID().toString());
                }
            }
        }

        // Lưu DailyLog kèm records
        return dailyLogRepo.save(log);
    }

    @Override
    public DailyLog updateLog(String id, DailyLog log) {
        if (dailyLogRepo.existsById(id)) {
            log.setLogId(id);

            // Gán DailyLog cho mỗi record và tạo ID nếu cần
            if (log.getRecords() != null) {
                for (HealthRecord record : log.getRecords()) {
                    record.setDailyLog(log);

                    if (record.getHealthRecordId() == null || record.getHealthRecordId().isBlank()) {
                        record.setHealthRecordId("HR" + UUID.randomUUID().toString().substring(0, 6));
                    }
                }
            }

            return dailyLogRepo.save(log);
        }
        return null;
    }

    @Override
    public void deleteLog(String id) {
        dailyLogRepo.deleteById(id);
    }
}