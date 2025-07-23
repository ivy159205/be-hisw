package com.example.Healthcare.service;

import com.example.Healthcare.DTO.DailyLogDTO; // <-- Import DTO
import com.example.Healthcare.model.DailyLog;
import java.time.LocalDate;
import java.util.List;

public interface DailyLogService {
    List<DailyLogDTO> getAllLogs(); // <-- Trả về List<DailyLogDTO>
    DailyLogDTO getLogById(Long id); // <-- Trả về DailyLogDTO

    // Các phương thức khác có thể giữ nguyên nếu chúng không trả về cho API getAll
    List<DailyLog> getLogsByUserId(Long userId);
    List<DailyLog> getLogsByUserIdAndDateRange(Long userId, LocalDate start, LocalDate end);
    
    DailyLogDTO createLog(DailyLog log);
    DailyLogDTO updateLog(Long id, DailyLog log);
    void deleteLog(Long id);
}