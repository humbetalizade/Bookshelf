package org.example.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Year;
import java.util.List;

@Entity
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @DateTimeFormat(pattern = "YYYY")
    private Year birthYear;


    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<BookEntity> books;



    public AuthorEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Year getBirthYear() {
        return birthYear;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthYear(Year birthYear) {
        this.birthYear = birthYear;
    }
}
