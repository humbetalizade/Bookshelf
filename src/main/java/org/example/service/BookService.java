package org.example.service;

import org.example.dto.bookDtos.BookCreateDto;
import org.example.entity.AuthorEntity;
import org.example.entity.GenresEntity;
import org.example.mapper.BookMapper;
import org.example.dto.bookDtos.BookDto;
import org.example.entity.BookEntity;
import org.example.repository.AuthorRepository;
import org.example.repository.BookRepository;
import org.example.repository.GenresRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService implements BookServiceInterface {

    BookRepository bookRepository;
    AuthorRepository authorRepository;
    GenresRepository genreRepository;

    @Override
    public List<BookDto> getAllBooks() {
        List<BookEntity>books = bookRepository.findAll();
        List<BookDto>dtoBooks=new ArrayList<>();
        for(BookEntity book:books){
            dtoBooks.add(BookMapper.toDto(book));
        }
        return dtoBooks;
    }

    @Override
    public void saveBook(BookCreateDto bookCreateDto) {
        BookEntity bookEntity= BookMapper.toEntityCreate(bookCreateDto);
        // Author
        AuthorEntity author = authorRepository.findById(bookCreateDto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author tapılmadı"));
        bookEntity.setAuthor(author);

        // Genres
        Set<GenresEntity> genres = new HashSet<>(genreRepository.findAllById(bookCreateDto.getGenreIds()));
        bookEntity.setGenres(genres);
        bookRepository.save(bookEntity);
    }



    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> searchBooks(String keyword) {
        List<BookEntity> books=bookRepository.findByTitleContainingIgnoreCase(keyword);
        List<BookDto> result=new ArrayList<>();
        for(BookEntity book:books){
           result.add(BookMapper.toDto(book));
        }
            return result;
    }
}
