package org.example.dto.bookDtos;

import java.util.List;

public class BookUpdateDto {
        private Long id;
        private String title;
        private Integer year;
        private Long authorId;
        private List<Long> genreIds;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setGenreIds(List<Long> genreIds) {
        this.genreIds = genreIds;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public List<Long> getGenreIds() {
        return genreIds;
    }
}
