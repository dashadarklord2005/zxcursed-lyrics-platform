package com.zxc.lyricannotations.service;

import com.zxc.lyricannotations.entity.Album;
import com.zxc.lyricannotations.entity.Rating;
import com.zxc.lyricannotations.entity.Song;
import com.zxc.lyricannotations.entity.User;
import com.zxc.lyricannotations.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public void rateSong(User user, Song song, int ratingValue) {
        Optional<Rating> existingRating = ratingRepository.findByUserIdAndSongId(user.getId(), song.getId());
        if (existingRating.isPresent()) {
            // Обновляем существующий рейтинг
            Rating rating = existingRating.get();
            rating.setRatingValue(ratingValue);
            ratingRepository.save(rating);
        } else {
            // Создаем новый рейтинг
            Rating rating = new Rating(ratingValue, user, song);
            ratingRepository.save(rating);
        }
    }

    public void rateAlbum(User user, Album album, int ratingValue) {
        Optional<Rating> existingRating = ratingRepository.findByUserIdAndAlbumId(user.getId(), album.getId());
        if (existingRating.isPresent()) {
            Rating rating = existingRating.get();
            rating.setRatingValue(ratingValue);
            ratingRepository.save(rating);
        } else {
            Rating rating = new Rating(ratingValue, user, album);
            ratingRepository.save(rating);
        }
    }

    public Double getAverageRatingForSong(Song song) {
        return ratingRepository.findAverageRatingBySongId(song.getId());
    }

    public Double getAverageRatingForAlbum(Album album) {
        return ratingRepository.findAverageRatingByAlbumId(album.getId());
    }

    public Long getRatingCountForSong(Song song) {
        return ratingRepository.countRatingsBySongId(song.getId());
    }

    public Long getRatingCountForAlbum(Album album) {
        return ratingRepository.countRatingsByAlbumId(album.getId());
    }

    public Integer getUserRatingForSong(User user, Song song) {
        Optional<Rating> rating = ratingRepository.findByUserIdAndSongId(user.getId(), song.getId());
        return rating.map(Rating::getRatingValue).orElse(0);
    }

    public Integer getUserRatingForAlbum(User user, Album album) {
        Optional<Rating> rating = ratingRepository.findByUserIdAndAlbumId(user.getId(), album.getId());
        return rating.map(Rating::getRatingValue).orElse(0);
    }
}