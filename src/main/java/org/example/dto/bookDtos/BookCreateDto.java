package org.example.dto.bookDtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class BookCreateDto {

    @NotBlank(message = "Kitab adı mütləqdir!")
    private String title;

    @Min(value = 1800, message = "İl 1800-dən böyük olmalıdır!")
    private Integer year;

    @NotNull(message = "Müəllif seçilməlidir!")
    private Long authorId;
    private String newAuthorName;
    private List<Long> genreIds;

    public String getNewAuthorName() {
        return newAuthorName;
    }

    public void setNewAuthorName(String newAuthorName) {
        this.newAuthorName = newAuthorName;
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
