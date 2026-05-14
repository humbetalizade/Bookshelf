package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.util.Set;


@Entity
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;


    @Positive
    @Column(name="publish_year")
    private Integer year;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id")
    private AuthorEntity author;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<GenresEntity> genres;


    public BookEntity() {}

    public BookEntity(Long id, String title, Integer year, AuthorEntity author, Set<GenresEntity> genres) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.author = author;
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public Set<GenresEntity> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenresEntity> genres) {
        this.genres = genres;
    }
}
