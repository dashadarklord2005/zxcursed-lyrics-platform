package com.zxc.lyricannotations.controller;

import com.zxc.lyricannotations.entity.User;
import com.zxc.lyricannotations.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserProfileController {

    private final UserService userService;

    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{username}")
    public String userProfile(@PathVariable String username, Model model) {
        try {
            User user = userService.findByUsername(username);

            // Подсчитываем статистику
            int albumCommentsCount = user.getAlbumComments() != null ? user.getAlbumComments().size() : 0;
            int songCommentsCount = user.getSongComments() != null ? user.getSongComments().size() : 0;
            int totalComments = albumCommentsCount + songCommentsCount;

            model.addAttribute("user", user);
            model.addAttribute("albumCommentsCount", albumCommentsCount);
            model.addAttribute("songCommentsCount", songCommentsCount);
            model.addAttribute("totalComments", totalComments);

            return "user-profile";
        } catch (RuntimeException e) {
            model.addAttribute("error", "User not found: " + username);
            return "error";
        }
    }
}