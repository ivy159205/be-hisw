package com.example.Healthcare.controller;

import com.example.Healthcare.model.Target;
import com.example.Healthcare.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/targets")
public class TargetController {

 @Autowired
private TargetService targetService;

@GetMapping
public List<Target> getAllTargets() {
    return targetService.getAllTargets();
}

@GetMapping("/{id}")
public ResponseEntity<Target> getTargetById(@PathVariable String id) {
    Target target = targetService.getTargetById(id);
    return target != null ? ResponseEntity.ok(target) : ResponseEntity.notFound().build();
}

@PostMapping
public ResponseEntity<Target> createTarget(@RequestBody Target target) {
    Target saved = targetService.createTarget(target);
    return ResponseEntity.ok(saved);
}

@PutMapping("/{id}")
public ResponseEntity<Target> updateTarget(@PathVariable String id, @RequestBody Target target) {
    Target updated = targetService.updateTarget(id, target);
    return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteTarget(@PathVariable String id) {
    targetService.deleteTarget(id);
    return ResponseEntity.noContent().build();
}
}
