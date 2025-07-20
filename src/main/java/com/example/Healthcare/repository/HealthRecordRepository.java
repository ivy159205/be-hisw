package com.example.Healthcare.repository;
import com.example.Healthcare.model.HealthRecord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {
    List<HealthRecord> findByDailyLogLogId(String logId);
}