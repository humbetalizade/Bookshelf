package org.example.mapper;

import org.example.dto.AuthorDto;
import org.example.entity.AuthorEntity;
import org.springframework.stereotype.Component;


@Component
public class AuthorMapper {


    public static AuthorDto toDto(AuthorEntity entity) {
        AuthorDto dto = new AuthorDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBirthYear(entity.getBirthYear());

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
