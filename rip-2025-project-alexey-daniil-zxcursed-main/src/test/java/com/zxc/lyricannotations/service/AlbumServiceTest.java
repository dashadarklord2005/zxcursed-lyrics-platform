package com.zxc.lyricannotations.service;

import com.zxc.lyricannotations.entity.Album;
import com.zxc.lyricannotations.repository.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlbumServiceTest {

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private AlbumService albumService;

    @Test
    void findAllAlbums_ShouldReturnAllAlbums() {
        // Given
        Album album = new Album();
        album.setTitle("Test Album");
        when(albumRepository.findAllByOrderByReleaseDateDesc()).thenReturn(List.of(album));

        // When
        List<Album> result = albumService.findAllAlbums();

        // Then
        assertEquals(1, result.size());
        assertEquals("Test Album", result.get(0).getTitle());
    }
}