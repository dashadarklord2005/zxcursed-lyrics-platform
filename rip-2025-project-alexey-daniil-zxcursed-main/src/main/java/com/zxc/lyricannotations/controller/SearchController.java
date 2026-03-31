package com.zxc.lyricannotations.controller;

import com.zxc.lyricannotations.entity.Song;
import com.zxc.lyricannotations.repository.SongRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private final SongRepository songRepository;

    public SearchController(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) {
        System.out.println("Search query: " + query);

        if (query == null || query.trim().isEmpty()) {
            model.addAttribute("songs", List.of());
            model.addAttribute("query", "");
            return "search-results";
        }

        List<Song> songs = songRepository.findByTitleContainingIgnoreCase(query.trim());
        System.out.println("Found " + songs.size() + " songs");

        model.addAttribute("songs", songs);
        model.addAttribute("query", query);
        return "search-results";
    }
}