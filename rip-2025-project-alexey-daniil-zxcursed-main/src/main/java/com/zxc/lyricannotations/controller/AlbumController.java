package com.zxc.lyricannotations.controller;

import com.zxc.lyricannotations.entity.Album;
import com.zxc.lyricannotations.entity.AlbumComment;
import com.zxc.lyricannotations.entity.User;
import com.zxc.lyricannotations.repository.AlbumRepository;
import com.zxc.lyricannotations.repository.SongRepository;
import com.zxc.lyricannotations.repository.AlbumCommentRepository;
import com.zxc.lyricannotations.service.RatingService;
import com.zxc.lyricannotations.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AlbumController {
    private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);

    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final AlbumCommentRepository albumCommentRepository;
    private final UserService userService;
    private final RatingService ratingService;

    public AlbumController(AlbumRepository albumRepository, SongRepository songRepository,
                           AlbumCommentRepository albumCommentRepository, UserService userService,
                           RatingService ratingService) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
        this.albumCommentRepository = albumCommentRepository;
        this.userService = userService;
        this.ratingService = ratingService;
    }

    @GetMapping("/album/{id}")
    public String albumDetail(@PathVariable Long id, Model model, Authentication authentication) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Album not found"));

        // Получаем рейтинги
        Double averageRating = ratingService.getAverageRatingForAlbum(album);
        Long ratingCount = ratingService.getRatingCountForAlbum(album);

        // Получаем рейтинг текущего пользователя (если авторизован)
        Integer userRating = 0;
        if (authentication != null && authentication.isAuthenticated()) {
            User user = userService.findByUsername(authentication.getName());
            userRating = ratingService.getUserRatingForAlbum(user, album);
        }

        model.addAttribute("album", album);
        model.addAttribute("songs", songRepository.findByAlbumIdOrderByTitle(id));
        model.addAttribute("comments", albumCommentRepository.findByAlbumIdOrderByCreatedAtDesc(id));
        model.addAttribute("averageRating", averageRating);
        model.addAttribute("ratingCount", ratingCount);
        model.addAttribute("userRating", userRating);

        return "album-detail";
    }

    @PostMapping("/album/{id}/comment")
    public String addComment(@PathVariable Long id, @RequestParam String text, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Album album = albumRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Album not found"));
            User user = userService.findByUsername(authentication.getName());

            AlbumComment comment = new AlbumComment(text, user, album);
            albumCommentRepository.save(comment);
        }
        return "redirect:/album/" + id;
    }

    @PostMapping("/album/comment/{id}/delete")
    public String deleteAlbumComment(@PathVariable Long id, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            try {
                Optional<AlbumComment> commentOptional = albumCommentRepository.findById(id);
                if (commentOptional.isPresent()) {
                    AlbumComment comment = commentOptional.get();
                    // Проверяем, что пользователь удаляет свой комментарий
                    if (comment.getUser().getUsername().equals(authentication.getName())) {
                        Long albumId = comment.getAlbum().getId();
                        albumCommentRepository.delete(comment);
                        logger.info("Album comment deleted successfully: {}", id);
                        return "redirect:/album/" + albumId;
                    }
                }
            } catch (Exception e) {
                logger.error("Error deleting album comment: {}", e.getMessage(), e);
            }
        }
        return "redirect:/";
    }

    @PostMapping("/album/{id}/rate")
    public String rateAlbum(@PathVariable Long id, @RequestParam int value, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            try {
                Optional<Album> albumOptional = albumRepository.findById(id);
                if (albumOptional.isPresent()) {
                    Album album = albumOptional.get();
                    User user = userService.findByUsername(authentication.getName());
                    ratingService.rateAlbum(user, album, value);
                    logger.info("User {} rated album {} with {}", user.getUsername(), album.getTitle(), value);
                }
            } catch (Exception e) {
                logger.error("Error rating album: {}", e.getMessage(), e);
            }
        }
        return "redirect:/album/" + id;
    }
}