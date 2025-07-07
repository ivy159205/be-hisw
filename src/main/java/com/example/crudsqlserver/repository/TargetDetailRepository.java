package com.example.crudsqlserver.repository;
import com.example.crudsqlserver.model.TargetDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TargetDetailRepository extends JpaRepository<TargetDetail, String> {
    List<TargetDetail> findByTargetTargetId(String targetId);
}
