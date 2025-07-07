package com.example.crudsqlserver.repository;
import com.example.crudsqlserver.model.HealthRecord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface HealthRecordRepository extends JpaRepository<HealthRecord, String> {
    List<HealthRecord> findByDailyLogLogId(String logId);
}