package org.example.service;

import org.example.dto.bookDtos.BookCreateDto;
import org.example.dto.bookDtos.BookDto;

import java.util.List;

public interface BookServiceInterface {

    //to get all books
    List<BookDto> getAllBooks();


    //to save the book to the DB
    void saveBook(BookCreateDto bookCreateDto);


    //remove the book from DB
    void deleteBook(Long id);

    List<BookDto> searchBooks(String keyword);

}
