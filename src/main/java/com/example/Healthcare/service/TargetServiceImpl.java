package com.example.Healthcare.service;

import com.example.Healthcare.model.Target;
import com.example.Healthcare.model.TargetDetail;

import com.example.Healthcare.repository.TargetRepository;
import com.example.Healthcare.repository.UserRepository;
import com.example.Healthcare.repository.MetricTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetServiceImpl implements TargetService {

    @Autowired
    private TargetRepository targetRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MetricTypeRepository metricTypeRepo;

    @Override
    public List<Target> getAllTargets() {
        return targetRepo.findAll();
    }

    @Override
    public Target getTargetById(Long id) {
        return targetRepo.findById(id).orElse(null);
    }

    @Override
    public Target createTarget(Target target) {

        // Kiểm tra và gán user từ DB
        if (target.getUser() != null && target.getUser().getUserId() != null) {
            userRepo.findById(target.getUser().getUserId())
                    .ifPresent(target::setUser);
        } else {
            throw new IllegalArgumentException("User ID is required for creating target");
        }

        // Gán target cho từng detail và xử lý MetricType
        if (target.getDetails() != null) {
            for (TargetDetail detail : target.getDetails()) {
                detail.setTarget(target);

                if (detail.getMetricType() != null && detail.getMetricType().getMetricId() != null) {
                    Long metricId = detail.getMetricType().getMetricId();

                    metricTypeRepo.findById(metricId).ifPresent(mt -> {
                        detail.setMetricType(mt);
                    });
                } else {
                    throw new IllegalArgumentException("MetricType ID is required for each TargetDetail");
                }
            }
        }

        return targetRepo.save(target);
    }

    @Override
    public Target updateTarget(Long id, Target updatedTarget) {
        return targetRepo.findById(id).map(existing -> {
            existing.setTitle(updatedTarget.getTitle());
            existing.setStatus(updatedTarget.getStatus());
            existing.setStartDate(updatedTarget.getStartDate());
            existing.setEndDate(updatedTarget.getEndDate());
            existing.setUser(updatedTarget.getUser());

            // Cập nhật details an toàn
            if (updatedTarget.getDetails() != null) {
                existing.getDetails().clear();
                for (TargetDetail detail : updatedTarget.getDetails()) {
                    detail.setTarget(existing);
                    existing.getDetails().add(detail);
                }
            }

            return targetRepo.save(existing);
        }).orElse(null);
    }

    @Override
    public void deleteTarget(Long id) {
        targetRepo.deleteById(id);
    }

    // --- TRIỂN KHAI PHƯƠNG THỨC NÀY ---
    @Override
    public long countActiveTargets() {
        // Sử dụng phương thức tìm kiếm được đặt tên (derived query method)
        // Spring Data JPA sẽ tự động tạo truy vấn cho bạn
        return targetRepo.countByStatus("In Progress");
    }

    @Override
    public List<Target> getTargetsByUserId(Long userId) {
        return targetRepo.findByUserUserId(userId);
    }
}
