package org.example.mapper;

import org.example.dto.AuthorDto;
import org.example.entity.AuthorEntity;
import org.example.entity.BookEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorMapper {


    public static AuthorDto toDto(AuthorEntity entity) {
        AuthorDto dto = new AuthorDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBirthYear(entity.getBirthYear());

        if (entity.getBooks() != null && !entity.getBooks().isEmpty()) {
            List<Long> bookIds = new ArrayList<>();
            for (BookEntity book : entity.getBooks()) {
                bookIds.add(book.getId());
            }
            dto.setBookIds(bookIds);
        }

        return dto;
    }


    public static AuthorEntity toEntity(AuthorDto dto) {
        AuthorEntity entity = new AuthorEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setBirthYear(dto.getBirthYear());
        // bookIds burada set edilmir — kitablar BookService tərəfindən əlavə olunur
        return entity;
    }

}
