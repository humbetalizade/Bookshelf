package org.example.dto;

import org.example.dto.bookDtos.BookDto;

import java.util.List;

public class GenresDto {

    private Long id;
    private String name;
    private List<Long> bookIds;


    public void setId(Long id) { this.id = id;}
    public void setName(String name) {
        this.name = name;
    }
    public void setBookIds(List<Long> bookIds) { this.bookIds = bookIds;}

    public Long getId() { return id; }
    public String getName() {
        return name;
    }
    public List<Long> getBookIds() {
        return bookIds;
    }
}
