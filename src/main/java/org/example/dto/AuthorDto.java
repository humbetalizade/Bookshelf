package org.example.dto;

import org.example.dto.bookDtos.BookDto;

import java.time.Year;
import java.util.List;

public class AuthorDto {

    private Long id;
    private String name;
    private Year birthYear;
    private List<Long> bookIds;


    public void setId(Long id) { this.id = id;}
    public void setBookIds(List<Long> bookIds) { this.bookIds = bookIds;}
    public void setName(String name) {
        this.name = name;
    }
    public void setBirthYear(Year birthYear) {
        this.birthYear = birthYear;
    }



    public Long getId() { return id;}
    public List<Long> getBookIds() {
        return bookIds;
    }
    public String getName() {
        return name;
    }
    public Year getBirthYear() {
        return birthYear;
    }
}
