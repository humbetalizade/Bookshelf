package org.example.controller;


import org.example.dto.bookDtos.BookDto;
import org.example.repository.AuthorRepository;
import org.example.repository.GenresRepository;
import org.example.service.BookService;
import org.example.service.LibraffScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.example.entity.BookEntity;
import org.example.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller()
@RequestMapping("/books")
public class BookController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;

    @Autowired
    private final GenresRepository genresRepository;

    public BookController(AuthorRepository authorRepository, BookRepository bookRepository, BookService bookService, GenresRepository genresRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.genresRepository = genresRepository;
    }

    @GetMapping()
    public String getAllBooks(Model model) {
        List<BookDto> books=bookService.getAllBooks();
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new BookEntity());
        model.addAttribute("genres", genresRepository.findAll());
        return "add-book";
    }

    @PostMapping("/add")
    public String addBookSubmit(@ModelAttribute("book") BookEntity book) {
        bookRepository.save(book);
        return "redirect:/books";
    }


    @GetMapping("/search")
    public String searchBook(@RequestParam("keyword") String keyword, Model model){
        List<BookEntity> results=bookRepository.findByTitleContainingIgnoreCase(keyword);
        if(results.isEmpty()){
            model.addAttribute("message", "Belə kitab yoxdur.");
        }else{
            model.addAttribute("books",results);
        }
        return "index";
    }



    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBooksForm(@PathVariable Long id, Model model) {
        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Yanlış kitab id: " + id));

        model.addAttribute("book", book);
        return "edit-book"; // edit-book.html açılır
    }


    @PostMapping("/edit/{id}")
    public String editBooksSubmit(@PathVariable Long id, @ModelAttribute BookEntity updateBook) {
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Yanlish kitab id: " + id));

        book.setAuthor(updateBook.getAuthor());
        book.setTitle(updateBook.getTitle());
        book.setYear(updateBook.getYear());

        bookRepository.save(book);
        return "redirect:/books";
    }


    @GetMapping("/scrape-libraff")
    public String scrapeLibraff(LibraffScraperService scraperService) {
        scraperService.scrapeBooks();
        return "redirect:/books";
    }


}
