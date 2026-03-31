package com.zxc.lyricannotations.repository;

import com.zxc.lyricannotations.entity.Annotation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnnotationRepository extends JpaRepository<Annotation, Long> {
    List<Annotation> findBySongIdOrderByCreatedAtDesc(Long songId);
    List<Annotation> findByUserIdOrderByCreatedAtDesc(Long userId);
}