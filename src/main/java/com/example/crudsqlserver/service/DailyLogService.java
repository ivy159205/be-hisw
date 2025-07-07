package com.example.crudsqlserver.service;

import com.example.crudsqlserver.model.DailyLog;

import java.time.LocalDate;
import java.util.List;

public interface DailyLogService {
    
    List<DailyLog> getAllLogs();
    DailyLog getLogById(String id);
    List<DailyLog> getLogsByUserId(String userId);
    List<DailyLog> getLogsByUserIdAndDateRange(String userId, LocalDate start, LocalDate end);
    DailyLog createLog(DailyLog log);
    DailyLog updateLog(String id, DailyLog log);
    void deleteLog(String id);
}