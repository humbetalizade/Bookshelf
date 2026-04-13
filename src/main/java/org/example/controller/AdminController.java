package org.example.controller;
import jakarta.validation.Valid;
import org.example.dto.bookDtos.BookCreateDto;
import org.example.dto.bookDtos.BookDto;
import org.example.repository.AuthorRepository;
import org.example.repository.GenresRepository;
import org.example.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final BookService bookService;
    private final AuthorRepository authorRepository;
    private final GenresRepository genresRepository;

    public AdminController(BookService bookService, AuthorRepository authorRepository, GenresRepository genresRepository) {
        this.bookService = bookService;
        this.authorRepository = authorRepository;
        this.genresRepository = genresRepository;
    }

    @GetMapping("/books")
    public String adminBooks(Model model){
        model.addAttribute("books", bookService.getAllBooks());
        return "admin/books";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new BookCreateDto());
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("genres", genresRepository.findAll());
        return "admin/add-book";
    }

    @PostMapping("/add")
    public String saveBook(@Valid @ModelAttribute("book") BookCreateDto dto, BindingResult result, Model model){
        if(result.hasErrors()){
            // Xəta varsa dropdown-ları yenidən doldur
            model.addAttribute("authors", authorRepository.findAll());
            model.addAttribute("genres", genresRepository.findAll());
            return "admin/add-book";  // Formaya qayıt (xətalar görünəcək)
        }

        bookService.saveBook(dto);
        return "redirect:/admin/books";  // Uğurlu → siyahıya
    }


    @GetMapping("/delete{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return "redirect:/admin/books";
    }

}
