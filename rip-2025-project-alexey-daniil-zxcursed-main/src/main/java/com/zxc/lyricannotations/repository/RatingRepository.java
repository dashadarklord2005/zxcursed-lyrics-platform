package com.zxc.lyricannotations.repository;

import com.zxc.lyricannotations.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    // Для песен
    Optional<Rating> findByUserIdAndSongId(Long userId, Long songId);
    List<Rating> findBySongId(Long songId);

    // Для альбомов
    Optional<Rating> findByUserIdAndAlbumId(Long userId, Long albumId);
    List<Rating> findByAlbumId(Long albumId);

    // Средние рейтинги
    @Query("SELECT AVG(r.ratingValue) FROM Rating r WHERE r.song.id = :songId")
    Double findAverageRatingBySongId(@Param("songId") Long songId);

    @Query("SELECT AVG(r.ratingValue) FROM Rating r WHERE r.album.id = :albumId")
    Double findAverageRatingByAlbumId(@Param("albumId") Long albumId);

    // Количество оценок
    @Query("SELECT COUNT(r) FROM Rating r WHERE r.song.id = :songId")
    Long countRatingsBySongId(@Param("songId") Long songId);

    @Query("SELECT COUNT(r) FROM Rating r WHERE r.album.id = :albumId")
    Long countRatingsByAlbumId(@Param("albumId") Long albumId);
}