package com.example.Healthcare.repository;

import com.example.Healthcare.model.DailyLog;
import com.example.Healthcare.model.User;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyLogRepository extends JpaRepository<DailyLog, Long> { // << THAY ĐỔI: String -> Long
    List<DailyLog> findByUser(User user);
    List<DailyLog> findByUserAndLogDateBetween(User user, LocalDate start, LocalDate end);
}