package com.example.Healthcare.repository;
import com.example.Healthcare.model.Target;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TargetRepository extends JpaRepository<Target, Long> {
  List<Target> findByUserUserId(Long userId);
long countByStatus(String status);

}