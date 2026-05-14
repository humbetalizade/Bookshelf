package org.example.entity;


import jakarta.persistence.*;
import java.util.List;

@Entity
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private Integer birthYear;


    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookEntity> books;

    @Override
    public String toString() {
        return super.toString();
    }

    public AuthorEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public String getName() {
        return name;
    }
    public Integer getBirthYear() {
        return birthYear;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }


}
