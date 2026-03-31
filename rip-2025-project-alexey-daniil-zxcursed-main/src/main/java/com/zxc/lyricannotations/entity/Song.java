package com.zxc.lyricannotations.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String lyrics;

    @Column(columnDefinition = "TEXT")
    private String description; // Добавляем поле описания

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album; // Может быть null

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Annotation> annotations = new ArrayList<>();

    // Конструкторы
    public Song() {}

    public Song(String title, String lyrics, Album album) {
        this.title = title;
        this.lyrics = lyrics;
        this.album = album;
    }

    public Song(String title, String lyrics, String description, Album album) {
        this.title = title;
        this.lyrics = lyrics;
        this.description = description;
        this.album = album;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getLyrics() { return lyrics; }
    public void setLyrics(String lyrics) { this.lyrics = lyrics; }

    public String getDescription() { return description; } // Добавляем геттер
    public void setDescription(String description) { this.description = description; } // Добавляем сеттер

    public Album getAlbum() { return album; }
    public void setAlbum(Album album) { this.album = album; }

    public List<Annotation> getAnnotations() { return annotations; }
    public void setAnnotations(List<Annotation> annotations) { this.annotations = annotations; }
}