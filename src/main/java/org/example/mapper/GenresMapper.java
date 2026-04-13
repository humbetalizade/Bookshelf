package org.example.mapper;

import org.example.dto.GenresDto;
import org.example.entity.GenresEntity;

public class GenresMapper {

    public static GenresDto toDto(GenresEntity entity){
        if(entity==null){
            return null;
        }
        GenresDto dto=new GenresDto();
        dto.setName(entity.getName());
        return dto;
    }


    public static GenresEntity toEntity(GenresDto dto){
        if (dto == null) return null;
        GenresEntity entity=new GenresEntity();
        entity.setName(dto.getName());
        return entity;
    }

}
