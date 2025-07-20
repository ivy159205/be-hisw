package com.example.Healthcare.controller;

import com.example.Healthcare.model.Target;
import com.example.Healthcare.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/targets")
@CrossOrigin(origins = "*")
public class TargetController {

    @Autowired
    private TargetService targetService;

    @GetMapping
    public List<Target> getAllTargets() {
        return targetService.getAllTargets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Target> getTargetById(@PathVariable Long id) {
        Target target = targetService.getTargetById(id);
        return target != null ? ResponseEntity.ok(target) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Target> createTarget(@RequestBody Target target) {
        Target saved = targetService.createTarget(target);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Target> updateTarget(@PathVariable Long id, @RequestBody Target target) {
        Target updated = targetService.updateTarget(id, target);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarget(@PathVariable Long id) {
        targetService.deleteTarget(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint mới hoặc sửa đổi để lấy target theo user ID
    @GetMapping("/user/{userId}")
    public List<Target> getTargetsByUserId(@PathVariable Long userId) { // Thay String bằng Long
        return targetService.getTargetsByUserId(userId);
    }

    // --- THÊM ENDPOINT NÀY ---
    @GetMapping("/active/count")
    public long countActiveTargets() {
        return targetService.countActiveTargets();
    }
}
