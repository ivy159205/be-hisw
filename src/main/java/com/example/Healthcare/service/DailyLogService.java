package com.example.Healthcare.service;

import com.example.Healthcare.model.DailyLog;
import java.time.LocalDate;
import java.util.List;

public interface DailyLogService {
    List<DailyLog> getAllLogs();
    DailyLog getLogById(Long id); // << THAY ĐỔI: String -> Long
    List<DailyLog> getLogsByUserId(Long userId);
    List<DailyLog> getLogsByUserIdAndDateRange(Long userId, LocalDate start, LocalDate end);
    DailyLog createLog(DailyLog log);
    DailyLog updateLog(Long id, DailyLog log); // << THAY ĐỔI: String -> Long
    void deleteLog(Long id); // << THAY ĐỔI: String -> Long
}