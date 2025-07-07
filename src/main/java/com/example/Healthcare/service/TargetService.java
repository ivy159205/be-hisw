package com.example.Healthcare.service;
import com.example.Healthcare.model.Target;

import java.util.List;

public interface TargetService {
    List<Target> getAllTargets();
    Target getTargetById(String id);
    Target createTarget(Target target);
    Target updateTarget(String id, Target updatedTarget);
    void deleteTarget(String id);
}