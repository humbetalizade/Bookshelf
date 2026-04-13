package org.example.service;

import org.example.dto.AuthorDto;
import org.example.dto.GenresDto;
import org.example.dto.bookDtos.BookCreateDto;
import org.example.entity.AuthorEntity;
import org.example.entity.GenresEntity;
import org.example.mapper.AuthorMapper;
import org.example.mapper.BookMapper;
import org.example.dto.bookDtos.BookDto;
import org.example.entity.BookEntity;
import org.example.mapper.GenresMapper;
import org.example.repository.AuthorRepository;
import org.example.repository.BookRepository;
import org.example.repository.GenresRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService implements BookServiceInterface {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenresRepository genresRepository;

    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       GenresRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genresRepository = genreRepository;
    }
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

        // Yeni müəllif logic-i
        if (bookCreateDto.getNewAuthorName() != null && !bookCreateDto.getNewAuthorName().trim().isEmpty()) {
            AuthorEntity newAuthor = new AuthorEntity();
            newAuthor.setName(bookCreateDto.getNewAuthorName());
            newAuthor = authorRepository.save(newAuthor);
            bookEntity.setAuthor(newAuthor);
        } else if (bookCreateDto.getAuthorId() != null) {
            AuthorEntity authorEntity = authorRepository.findById(bookCreateDto.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Müəllif tapılmadı"));
            bookEntity.setAuthor(authorEntity);
        }
        // ✅ Genres
        if (bookCreateDto.getGenreIds() != null && !bookCreateDto.getGenreIds().isEmpty()) {
            Set<GenresEntity> genres = new HashSet<>(genresRepository.findAllById(bookCreateDto.getGenreIds()));
            bookEntity.setGenres(genres);
        }

        // ✅ Yalnız 1 dəfə save
        bookRepository.save(bookEntity);
    }



    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }



    @Override
    public List<BookDto> searchBooks(String keyword) {
        if(keyword == null || keyword.trim().isEmpty()) {
            return Collections.emptyList();  // Və ya bütün kitablar
        }
        List<BookEntity> books = bookRepository.findByTitleContainingIgnoreCase(keyword.trim());
        return books.stream().map(BookMapper::toDto).collect(Collectors.toList());  // Stream API ✨
    }



    public List<AuthorDto> getAllAuthors(){
        if (authorRepository==null){
            return List.of();
        }
        return authorRepository.findAll().stream()
                .map(AuthorMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<GenresDto> getAllGenres() {
        if (genresRepository == null){
            return List.of();
        }
        return genresRepository.findAll().stream()
                .map(GenresMapper::toDto)
                .collect(Collectors.toList());
    }
}
