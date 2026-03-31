package com.zxc.lyricannotations.controller;

import com.zxc.lyricannotations.entity.Album;
import com.zxc.lyricannotations.entity.Song;
import com.zxc.lyricannotations.repository.AlbumRepository;
import com.zxc.lyricannotations.repository.SongRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

    public HomeController(AlbumRepository albumRepository, SongRepository songRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Album> albums = albumRepository.findAllByOrderByReleaseDateDesc();
        List<Song> songs = songRepository.findAllByOrderByTitle();

        System.out.println("Albums count: " + albums.size());
        System.out.println("Songs count: " + songs.size());

        // Обновленные данные об артисте
        model.addAttribute("artistName", "ZXC CURSED");
        model.addAttribute("artistBio", "Украинский исполнитель родом из Киева, ключевая фигура в андерграундном движении «dead inside». В основном пишет фонк. В его треках можно найти большое количество отсылок на аниме, Dota 2 и культуру «мёртвых внутри».");
        model.addAttribute("artistImage", "/images/zxc-cursed.jpg");

        model.addAttribute("albums", albums);
        model.addAttribute("songs", songs);
        return "home";
    }
}