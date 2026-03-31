package com.zxc.lyricannotations.repository;

import com.zxc.lyricannotations.entity.SongComment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SongCommentRepository extends JpaRepository<SongComment, Long> {
    List<SongComment> findBySongIdOrderByCreatedAtDesc(Long songId);
}