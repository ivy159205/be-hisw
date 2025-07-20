package com.example.Healthcare.service;

import com.example.Healthcare.model.DailyLog;

import java.time.LocalDate;
import java.util.List;

public interface DailyLogService {
    
    List<DailyLog> getAllLogs();
    DailyLog getLogById(String id);
    List<DailyLog> getLogsByUserId(Long userId);
    List<DailyLog> getLogsByUserIdAndDateRange(Long userId, LocalDate start, LocalDate end);
    DailyLog createLog(DailyLog log);
    DailyLog updateLog(String id, DailyLog log);
    void deleteLog(String id);
}