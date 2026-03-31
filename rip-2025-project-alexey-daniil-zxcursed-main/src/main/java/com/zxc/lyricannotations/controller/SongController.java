package com.zxc.lyricannotations.controller;

import com.zxc.lyricannotations.entity.Song;
import com.zxc.lyricannotations.entity.SongComment;
import com.zxc.lyricannotations.entity.User;
import com.zxc.lyricannotations.repository.SongRepository;
import com.zxc.lyricannotations.repository.SongCommentRepository;
import com.zxc.lyricannotations.service.RatingService;
import com.zxc.lyricannotations.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class SongController {
    private static final Logger logger = LoggerFactory.getLogger(SongController.class);

    private final SongRepository songRepository;
    private final SongCommentRepository songCommentRepository;
    private final UserService userService;
    private final RatingService ratingService;

    public SongController(SongRepository songRepository, SongCommentRepository songCommentRepository,
                          UserService userService, RatingService ratingService) {
        this.songRepository = songRepository;
        this.songCommentRepository = songCommentRepository;
        this.userService = userService;
        this.ratingService = ratingService;
    }

    @GetMapping("/song/{id}")
    public String songDetail(@PathVariable Long id, Model model, Authentication authentication) {
        logger.info("=== LOADING SONG WITH ID: {} ===", id);

        try {
            Optional<Song> songOptional = songRepository.findById(id);
            if (songOptional.isEmpty()) {
                logger.error("Song not found with id: {}", id);
                model.addAttribute("error", "Song not found with id: " + id);
                return "error";
            }

            Song song = songOptional.get();
            logger.info("Found song: {}", song.getTitle());

            // Получаем рейтинги
            Double averageRating = ratingService.getAverageRatingForSong(song);
            Long ratingCount = ratingService.getRatingCountForSong(song);

            // Получаем рейтинг текущего пользователя (если авторизован)
            Integer userRating = 0;
            if (authentication != null && authentication.isAuthenticated()) {
                User user = userService.findByUsername(authentication.getName());
                userRating = ratingService.getUserRatingForSong(user, song);
            }

            List<SongComment> comments = songCommentRepository.findBySongIdOrderByCreatedAtDesc(id);

            model.addAttribute("song", song);
            model.addAttribute("comments", comments);
            model.addAttribute("averageRating", averageRating);
            model.addAttribute("ratingCount", ratingCount);
            model.addAttribute("userRating", userRating);

            return "song-detail";

        } catch (Exception e) {
            logger.error("Error loading song with id {}: {}", id, e.getMessage(), e);
            model.addAttribute("error", "Error: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping("/song/{id}/comment")
    public String addComment(@PathVariable Long id, @RequestParam String text, Authentication authentication) {
        logger.info("Adding comment to song {} by user {}", id,
                authentication != null ? authentication.getName() : "anonymous");

        if (authentication != null && authentication.isAuthenticated()) {
            try {
                Optional<Song> songOptional = songRepository.findById(id);
                if (songOptional.isEmpty()) {
                    logger.error("Song not found for comment: {}", id);
                    return "redirect:/error";
                }

                Song song = songOptional.get();
                User user = userService.findByUsername(authentication.getName());

                SongComment comment = new SongComment(text, user, song);
                songCommentRepository.save(comment);
                logger.info("Comment saved successfully");

            } catch (Exception e) {
                logger.error("Error saving comment: {}", e.getMessage(), e);
            }
        }
        return "redirect:/song/" + id;
    }

    @PostMapping("/song/comment/{id}/delete")
    public String deleteSongComment(@PathVariable Long id, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            try {
                Optional<SongComment> commentOptional = songCommentRepository.findById(id);
                if (commentOptional.isPresent()) {
                    SongComment comment = commentOptional.get();
                    // Проверяем, что пользователь удаляет свой комментарий
                    if (comment.getUser().getUsername().equals(authentication.getName())) {
                        Long songId = comment.getSong().getId();
                        songCommentRepository.delete(comment);
                        logger.info("Song comment deleted successfully: {}", id);
                        return "redirect:/song/" + songId;
                    }
                }
            } catch (Exception e) {
                logger.error("Error deleting song comment: {}", e.getMessage(), e);
            }
        }
        return "redirect:/";
    }

    @PostMapping("/song/{id}/rate")
    public String rateSong(@PathVariable Long id, @RequestParam int value, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            try {
                Optional<Song> songOptional = songRepository.findById(id);
                if (songOptional.isPresent()) {
                    Song song = songOptional.get();
                    User user = userService.findByUsername(authentication.getName());
                    ratingService.rateSong(user, song, value);
                    logger.info("User {} rated song {} with {}", user.getUsername(), song.getTitle(), value);
                }
            } catch (Exception e) {
                logger.error("Error rating song: {}", e.getMessage(), e);
            }
        }
        return "redirect:/song/" + id;
    }
}