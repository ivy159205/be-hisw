package com.example.Healthcare.service;

import com.example.Healthcare.model.MetricType;
import com.example.Healthcare.model.Target;
import com.example.Healthcare.model.TargetDetail;
import com.example.Healthcare.DTO.TargetDto;
import com.example.Healthcare.DTO.TargetDetailDto;

import java.util.stream.Collectors;
import com.example.Healthcare.repository.TargetRepository;
import com.example.Healthcare.repository.UserRepository;
import com.example.Healthcare.repository.MetricTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

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

        // 1. Kiểm tra và gán User từ DB (giữ nguyên)
        if (target.getUser() != null && target.getUser().getUserId() != null) {
            userRepo.findById(target.getUser().getUserId())
                    .ifPresent(target::setUser);
        } else {
            throw new IllegalArgumentException("User ID is required for creating target");
        }

        // 2. Xử lý Details (SỬA LẠI HOÀN TOÀN LOGIC)
        if (target.getDetails() != null && !target.getDetails().isEmpty()) {
            for (TargetDetail detail : target.getDetails()) {
                // Gán Target cha cho mỗi Detail
                detail.setTarget(target);

                // Kiểm tra xem frontend có gửi 'metricId' không
                if (detail.getMetricId() != null) {
                    // Dùng metricId đó để tìm MetricType trong DB
                    Long metricId = detail.getMetricId();
                    MetricType metricType = metricTypeRepo.findById(metricId)
                            .orElseThrow(() -> new IllegalArgumentException("Invalid MetricType ID: " + metricId));

                    // Gán đối tượng MetricType đầy đủ vào detail
                    detail.setMetricType(metricType);

                } else {
                    // Nếu không có metricId, ném ra lỗi
                    throw new IllegalArgumentException("MetricType ID is required for each TargetDetail");
                }
            }
        }

        // 3. Lưu Target và các details liên quan
        return targetRepo.save(target);
    }

    @Override
    public Target updateTarget(Long id, Target updatedTarget) {
        // Tìm Target đang tồn tại trong DB
        Target existingTarget = targetRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Target not found with id: " + id));

        // Cập nhật các trường của Target cha
        existingTarget.setTitle(updatedTarget.getTitle());
        existingTarget.setStatus(updatedTarget.getStatus());
        existingTarget.setStartDate(updatedTarget.getStartDate());
        existingTarget.setEndDate(updatedTarget.getEndDate());

        // Xóa các detail cũ để chuẩn bị thêm detail mới đã được cập nhật
        existingTarget.getDetails().clear();

        // Xử lý các detail mới từ request (với logic đúng)
        if (updatedTarget.getDetails() != null && !updatedTarget.getDetails().isEmpty()) {
            for (TargetDetail updatedDetail : updatedTarget.getDetails()) {
                // Gán lại Target cha
                updatedDetail.setTarget(existingTarget);

                // TÁI SỬ DỤNG LOGIC XỬ LÝ METRIC ID ĐÚNG
                if (updatedDetail.getMetricId() != null) {
                    Long metricId = updatedDetail.getMetricId();
                    MetricType metricType = metricTypeRepo.findById(metricId)
                            .orElseThrow(() -> new IllegalArgumentException("Invalid MetricType ID: " + metricId));
                    updatedDetail.setMetricType(metricType);
                } else {
                    throw new IllegalArgumentException("MetricType ID is required for each TargetDetail");
                }
                // Thêm detail đã được xử lý đúng vào Target
                existingTarget.getDetails().add(updatedDetail);
            }
        }

        // Lưu lại Target với thông tin đã được cập nhật chính xác
        return targetRepo.save(existingTarget);
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
    @Transactional(readOnly = true)
    public List<TargetDto> getTargetDTOsByUserId(Long userId) {
        List<Target> targets = targetRepo.findByUserUserId(userId);

        return targets.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private TargetDto convertToDto(Target target) {
        TargetDto dto = new TargetDto();
        dto.setTargetId(target.getTargetId());
        dto.setTitle(target.getTitle());
        dto.setStatus(target.getStatus());
        dto.setStartDate(target.getStartDate());
        dto.setEndDate(target.getEndDate());
        dto.setUserId(target.getUser().getUserId());

        List<TargetDetailDto> detailDtos = target.getDetails().stream().map(detail -> {
            TargetDetailDto d = new TargetDetailDto();
            d.setTdetailId(detail.getDetailId());
            d.setTargetValue(detail.getTargetValue());
            d.setComparisonType(detail.getComparisonType());
            d.setAggregationType(detail.getAggregationType());

            // **THÊM BƯỚC KIỂM TRA NULL Ở ĐÂY**
            // Chỉ lấy metricId nếu đối tượng metricType không bị null
            if (detail.getMetricType() != null) {
                d.setMetricId(detail.getMetricType().getMetricId());
            } else {
                // Nếu metricType là null, chúng ta có thể gán null cho DTO
                // để tránh làm sập ứng dụng.
                d.setMetricId(null);
            }

            return d;
        }).collect(Collectors.toList());

        dto.setDetails(detailDtos);
        return dto;
    }

}
