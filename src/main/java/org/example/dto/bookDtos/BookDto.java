package org.example.dto.bookDtos;

import org.example.dto.AuthorDto;
import org.example.dto.GenresDto;

import java.util.List;

public class BookDto {

    private Long id;
    private String title;
    private Integer year;
    private AuthorDto author;
    private List<String> genres;


    public Long getId() { return id; }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public List<String> getGenres() {
        return genres;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public void setGenres(List<String> genre) {
        this.genres = genre;
    }
}
