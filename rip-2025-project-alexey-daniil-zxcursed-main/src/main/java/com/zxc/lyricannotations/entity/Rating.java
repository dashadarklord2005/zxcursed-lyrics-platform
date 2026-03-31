package com.zxc.lyricannotations.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating_value")
    private int ratingValue; // 1-5

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id")
    private Song song;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    private LocalDateTime createdAt;

    // Конструкторы
    public Rating() {
        this.createdAt = LocalDateTime.now();
    }

    public Rating(int ratingValue, User user, Song song) {
        this.ratingValue = ratingValue;
        this.user = user;
        this.song = song;
        this.createdAt = LocalDateTime.now();
    }

    public Rating(int ratingValue, User user, Album album) {
        this.ratingValue = ratingValue;
        this.user = user;
        this.album = album;
        this.createdAt = LocalDateTime.now();
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getRatingValue() { return ratingValue; }
    public void setRatingValue(int ratingValue) { this.ratingValue = ratingValue; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Song getSong() { return song; }
    public void setSong(Song song) { this.song = song; }

    public Album getAlbum() { return album; }
    public void setAlbum(Album album) { this.album = album; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}