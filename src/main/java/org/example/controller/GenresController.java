package org.example.controller;


import org.springframework.ui.Model;
import org.example.repository.GenresRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/genres")
public class GenresController {

    private final GenresRepository genresRepository;

    public GenresController(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }


    @GetMapping
    public String listGenres(Model model){
        model.addAttribute("genres", genresRepository.findAll());
        return "genres";
    }
}
