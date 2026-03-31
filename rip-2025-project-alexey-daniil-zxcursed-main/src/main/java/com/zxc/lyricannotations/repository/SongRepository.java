package com.zxc.lyricannotations.repository;

import com.zxc.lyricannotations.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByTitleContainingIgnoreCase(String title);
    List<Song> findByAlbumIdOrderByTitle(Long albumId);
    List<Song> findAllByOrderByTitle();
    List<Song> findByAlbumIsNullOrderByTitle();
}