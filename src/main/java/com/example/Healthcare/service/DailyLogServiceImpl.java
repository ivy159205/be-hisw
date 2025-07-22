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
    public DailyLog getLogById(Long id) { // << THAY ĐỔI: String -> Long
        return dailyLogRepo.findById(id).orElse(null);
    }

    @Override
    public List<DailyLog> getLogsByUserId(Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            return dailyLogRepo.findByUser(user);
        }
        return List.of();
    }

    @Override
    public List<DailyLog> getLogsByUserIdAndDateRange(Long userId, LocalDate start, LocalDate end) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            return dailyLogRepo.findByUserAndLogDateBetween(user, start, end);
        }
        return List.of();
    }

    @Override
    public DailyLog createLog(DailyLog log) {
        // ID sẽ được tự động tạo bởi database, không cần set ở đây.
        if (log.getRecords() != null && !log.getRecords().isEmpty()) {
            for (var record : log.getRecords()) {
                record.setDailyLog(log);
            }
        }
        return dailyLogRepo.save(log);
    }

    @Override
    public DailyLog updateLog(Long id, DailyLog log) { // << THAY ĐỔI: String -> Long
        if (dailyLogRepo.existsById(id)) {
            log.setLogId(id); // Gán ID cho đối tượng để JPA biết đây là lệnh update

            if (log.getRecords() != null) {
                for (HealthRecord record : log.getRecords()) {
                    record.setDailyLog(log);
                }
            }
            return dailyLogRepo.save(log);
        }
        return null;
    }

    @Override
    public void deleteLog(Long id) { // << THAY ĐỔI: String -> Long
        dailyLogRepo.deleteById(id);
    }
}