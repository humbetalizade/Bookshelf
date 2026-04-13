package org.example.controller;


import org.example.dto.bookDtos.BookCreateDto;
import org.example.dto.bookDtos.BookDto;
import org.example.service.BookService;
import org.example.service.LibraffScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/")
public class BookController {

    private final BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        //List<BookDto> books = bookService.getAllBooks();
        model.addAttribute("books", List.of());
        return "index";
    }

    @GetMapping("/books/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new BookCreateDto());
        model.addAttribute("authors", bookService.getAllAuthors());
        model.addAttribute("genres", bookService.getAllGenres());
        return "add-book";
    }

    @PostMapping("/books/add")
    public String addBookSubmit(@ModelAttribute("book") BookCreateDto book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }


    @GetMapping("/search")
    public String searchBook(@RequestParam(value = "keyword", required = false) String keyword, Model model){
        List<BookDto> results=bookService.searchBooks(keyword);
        if(results.isEmpty()){
            model.addAttribute("message", "Belə kitab yoxdur.");
        }else{
            model.addAttribute("books",results);
        }
        return "index";
    }



//    @GetMapping("/delete/{id}")
//    public String deleteBook(@PathVariable Long id) {
//        bookService.deleteBook(id);
//        return "redirect:/books";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editBooksForm(@PathVariable Long id, Model model) {
//        BookEntity book = bookRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Yanlış kitab id: " + id));
//
//        model.addAttribute("book", book);
//        return "edit-book"; // edit-book.html açılır
//    }


//    @PostMapping("/edit/{id}")
//    public String editBooksSubmit(@PathVariable Long id, @ModelAttribute BookEntity updateBook) {
//        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Yanlish kitab id: " + id));
//
//        book.setAuthor(updateBook.getAuthor());
//        book.setTitle(updateBook.getTitle());
//        book.setYear(updateBook.getYear());
//
//        bookRepository.save(book);
//        return "redirect:/books";
//    }
//
//
    @GetMapping("/scrape-libraff")
    public String scrapeLibraff(LibraffScraperService scraperService) {
        scraperService.scrapeBooks();
        return "redirect:/books";
    }


}
