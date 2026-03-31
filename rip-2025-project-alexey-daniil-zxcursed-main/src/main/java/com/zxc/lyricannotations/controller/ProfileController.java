package com.zxc.lyricannotations.controller;

import com.zxc.lyricannotations.entity.User;
import com.zxc.lyricannotations.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            User user = userService.findByUsername(authentication.getName());
            model.addAttribute("user", user);
            return "profile";
        }
        return "redirect:/login";
    }

    @PostMapping("/upload-avatar")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile file,
                               Authentication authentication,
                               RedirectAttributes redirectAttributes) {
        if (authentication != null && authentication.isAuthenticated() && !file.isEmpty()) {
            try {
                User user = userService.findByUsername(authentication.getName());
                String avatarData = userService.saveAvatar(file, user.getUsername());
                user.setProfilePicture(avatarData);
                userService.updateUserProfile(user);
                redirectAttributes.addFlashAttribute("success", "Avatar updated successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "Failed to upload avatar: " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Please select a file to upload");
        }
        return "redirect:/profile";
    }

    @PostMapping("/update-profile")
    public String updateProfile(@RequestParam("bio") String bio,
                                Authentication authentication,
                                RedirectAttributes redirectAttributes) {
        if (authentication != null && authentication.isAuthenticated()) {
            User user = userService.findByUsername(authentication.getName());
            user.setBio(bio);
            userService.updateUserProfile(user);
            redirectAttributes.addFlashAttribute("success", "Profile updated successfully!");
        }
        return "redirect:/profile";
    }
}