package com.zxc.lyricannotations.repository;

import com.zxc.lyricannotations.entity.AlbumComment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlbumCommentRepository extends JpaRepository<AlbumComment, Long> {
    List<AlbumComment> findByAlbumIdOrderByCreatedAtDesc(Long albumId);
}