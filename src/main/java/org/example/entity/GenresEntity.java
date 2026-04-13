package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Entity
@Table(name="genres")
public class GenresEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<BookEntity> books;

    public GenresEntity() {};

    @Override
    public String toString() {
        return "name " + name;
    }

    public GenresEntity(String genre) {
        this.name=genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(Set<BookEntity> books) {
        this.books = books;
    }

    public Set<BookEntity> getBooks() {
        return books;
    }
}


