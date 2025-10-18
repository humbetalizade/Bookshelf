package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


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


    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;


    public BookEntity() {}

    public BookEntity(String title, AuthorEntity author, Integer year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public Integer getYear() {
        return year;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
