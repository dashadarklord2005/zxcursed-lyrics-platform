package com.zxc.lyricannotations.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "song_comment")
public class SongComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id")
    private Song song;

    private LocalDateTime createdAt;

    public SongComment() {
        this.createdAt = LocalDateTime.now();
    }

    public SongComment(String text, User user, Song song) {
        this.text = text;
        this.user = user;
        this.song = song;
        this.createdAt = LocalDateTime.now();
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Song getSong() { return song; }
    public void setSong(Song song) { this.song = song; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}