package org.example.controller;


import org.springframework.ui.Model;
import org.example.entity.BookEntity;
import org.example.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller()
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new BookEntity());
        return "add-book"; // add-book.html göstər
    }

    @PostMapping("/add")
    public String addBookSubmit(@ModelAttribute BookEntity book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
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

}
