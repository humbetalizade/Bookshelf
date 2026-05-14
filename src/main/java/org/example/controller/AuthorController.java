package org.example.controller;


import org.example.service.AuthorService;
import org.springframework.ui.Model;
import org.example.repository.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    private final AuthorService authorService;


    public AuthorController(AuthorRepository authorRepository, AuthorService authorService) {
        this.authorRepository = authorRepository;
        this.authorService = authorService;
    }


    @GetMapping
    public String listAuthors(Model model){
        model.addAttribute("authors", authorService.getAllAuthors());
        return "authors";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        if(authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        }
        return "redirect:/authors";  // ✅ Refresh xətası yox
    }
}
