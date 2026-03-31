package com.zxc.lyricannotations.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "album_comment")
public class AlbumComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    private LocalDateTime createdAt;

    public AlbumComment() {
        this.createdAt = LocalDateTime.now();
    }

    public AlbumComment(String text, User user, Album album) {
        this.text = text;
        this.user = user;
        this.album = album;
        this.createdAt = LocalDateTime.now();
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Album getAlbum() { return album; }
    public void setAlbum(Album album) { this.album = album; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
