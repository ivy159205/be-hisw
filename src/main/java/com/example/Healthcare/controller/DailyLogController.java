package com.example.Healthcare.controller;

import com.example.Healthcare.model.DailyLog;
import com.example.Healthcare.service.DailyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/dailylogs")
public class DailyLogController {

    @Autowired
    private DailyLogService dailyLogService;

    @GetMapping
    public List<DailyLog> getAllLogs() {
        return dailyLogService.getAllLogs();
    }

    @GetMapping("/{id}")
    public DailyLog getLogById(@PathVariable String id) {
        return dailyLogService.getLogById(id);
    }

    @GetMapping("/by-user/{userId}")
    public List<DailyLog> getLogsByUserId(@PathVariable Long userId) {
        return dailyLogService.getLogsByUserId(userId);
    }

    @GetMapping("/by-user/{userId}/range")
    public List<DailyLog> getLogsByUserIdAndRange(
            @PathVariable Long userId,
            @RequestParam("start") String start,
            @RequestParam("end") String end) {
        return dailyLogService.getLogsByUserIdAndDateRange(userId, LocalDate.parse(start), LocalDate.parse(end));
    }

    @PostMapping
    public DailyLog createLog(@RequestBody DailyLog log) {
        return dailyLogService.createLog(log);
    }

    @PutMapping("/{id}")
    public DailyLog updateLog(@PathVariable String id, @RequestBody DailyLog log) {
        return dailyLogService.updateLog(id, log);
    }

    @DeleteMapping("/{id}")
    public void deleteLog(@PathVariable String id) {
        dailyLogService.deleteLog(id);
    }
}
