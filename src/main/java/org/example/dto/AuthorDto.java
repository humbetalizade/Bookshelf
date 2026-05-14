package org.example.dto;

import java.util.List;
import java.util.Objects;

public class AuthorDto {

    private Long id;
    private String name;
    private Integer birthYear;
    private List<Long> bookIds;


    public void setId(Long id) { this.id = id;}
    public void setBookIds(List<Long> bookIds) { this.bookIds = bookIds;}
    public void setName(String name) {
        this.name = name;
    }
    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }



    public Long getId() { return id;}
    public List<Long> getBookIds() {
        return bookIds;
    }
    public String getName() {
        return name;
    }
    public Integer getBirthYear() {
        return birthYear;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDto authorDto = (AuthorDto) o;
        return Objects.equals(name, authorDto.name) && Objects.equals(birthYear, authorDto.birthYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthYear);
    }
}
