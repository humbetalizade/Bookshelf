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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService implements BookServiceInterface {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenresRepository genresRepository;



    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       GenresRepository genreRepository, AuthorMapper authorMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genresRepository = genreRepository;
    }



    //Bütün kitabları qaytarır
    @Override
    public List<BookDto> getAllBooks() {
        List<BookEntity>books = bookRepository.findAll();
        List<BookDto>dtoBooks=new ArrayList<>();
        for(BookEntity book:books){
            dtoBooks.add(BookMapper.toDto(book));
        }
        return dtoBooks;
    }




    //Jsoupla DB-ya kitab əlavə edir
    public void importBooksFromAlinino() throws IOException, InterruptedException {
        Document doc = Jsoup.connect(
                        "https://alinino.az/collection/knigi-na-azerbaydzhanskom-yazyke")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                .timeout(10000)
                .get();

        Elements cards = doc.select("a.product-card__title");
        System.out.println("Tapılan kitab sayı: " + cards.size());

        for (Element card : cards) {
            String title = card.text();
            String productUrl = "https://alinino.az" + card.attr("href");

            // Kitabın öz səhifəsinə gir
            Document productDoc = Jsoup.connect(productUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                    .timeout(10000)
                    .get();

            // Müəllif adını çək
            String author = "";
            Elements properties = productDoc.select(".product__properties-main-item");
            for (Element prop : properties) {
                if (prop.text().contains("Müəllif:")) {
                    author = prop.select("a").text();
                    break;
                }
            }
            System.out.println("Müəllif: " + author); // ← bura əlavə et

            if (!bookRepository.existsByTitle(title)) {
                BookEntity book = new BookEntity();
                AuthorEntity authorEntity=new AuthorEntity();
                authorEntity.setName(author);
                book.setTitle(title);
                book.setAuthor(authorEntity);
                book.setYear(book.getYear());
                bookRepository.save(book);
            } else {
                System.out.println("Artıq var: " + title);
            }

            System.out.println("Kitab: " + title + " | Müəllif: " + author);

            Thread.sleep(1000); // hər requestdən sonra gözlə
        }

    }





    //Yeni kitab əlavə etmək
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




    //Kitabı silmək
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }




    //Kitabı axtarmaq
    @Override
    public List<BookDto> searchBooks(String keyword) {
        if(keyword == null || keyword.trim().isEmpty()) {
            return Collections.emptyList();  // Və ya bütün kitablar
        }
        List<BookEntity> books = bookRepository.findByTitleContainingIgnoreCase(keyword.trim());
        return books.stream().map(BookMapper::toDto).collect(Collectors.toList());  // Stream API ✨
    }


    @Transactional
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
