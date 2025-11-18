package org.example.mapper;
import org.example.dto.bookDtos.BookCreateDto;
import org.example.dto.bookDtos.BookDto;
import org.example.dto.bookDtos.BookUpdateDto;
import org.example.entity.BookEntity;
import org.example.entity.GenresEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BookMapper {


    //GET / LIST / SEARCH
    public static BookDto toDto(BookEntity entity) {
        if(entity==null)
            return null;

        BookDto dto = new BookDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setYear(entity.getYear());

        // AUTHOR
        if (entity.getAuthor() != null) {
            dto.setAuthor(AuthorMapper.toDto(entity.getAuthor()));
        }


        dto.setGenres(entity.getGenres() != null ?
                entity.getGenres().stream()
                        .map(GenresEntity::getName)
                        .collect(Collectors.toList()) :
                new ArrayList<>()
        );
        return dto;
    }

    // CREATE üçün
    public static BookEntity toEntityCreate(BookCreateDto dto) {
        if(dto==null) return null;

        BookEntity entity = new BookEntity();
        entity.setTitle(dto.getTitle());
        entity.setYear(dto.getYear());
        // Author və Genres Service layer-də set olunacaq
        return entity;
    }

    // UPDATE üçün
    public static void updateEntityFromDto(BookEntity entity, BookUpdateDto dto) {
        if(entity==null || dto==null) return;
        entity.setTitle(dto.getTitle());
        entity.setYear(dto.getYear());
        // Author və Genres Service layer-də set olunacaq
    }
}