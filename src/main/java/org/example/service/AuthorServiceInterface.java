package org.example.service;

import org.example.dto.AuthorDto;
import org.example.dto.bookDtos.BookCreateDto;
import org.example.dto.bookDtos.BookDto;

import java.util.List;
import java.util.Set;

public interface AuthorServiceInterface {

    //to get all books
    Set<AuthorDto> getAllAuthors();

    //remove the book from DB
    void deleteBook(Long id);

}
