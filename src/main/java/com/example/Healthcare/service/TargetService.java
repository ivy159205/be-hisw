package com.example.Healthcare.service;
import com.example.Healthcare.DTO.TargetDto;
import com.example.Healthcare.model.Target;

import java.util.List;

public interface TargetService {
    List<Target> getAllTargets();
    Target getTargetById(Long id);
    Target createTarget(Target target);
    Target updateTarget(Long id, Target updatedTarget);
    void deleteTarget(Long id);
    
      List<TargetDto> getTargetDTOsByUserId(Long userId);// Thay String bằng Long
    // --- THÊM PHƯƠNG THỨC NÀY ---
    long countActiveTargets();
}