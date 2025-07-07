package com.example.Healthcare.repository;
import com.example.Healthcare.model.Target;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TargetRepository extends JpaRepository<Target, String> {
    List<Target> findByUserUserId(String userId);
}