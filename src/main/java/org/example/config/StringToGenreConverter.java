package org.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.example.entity.GenresEntity;
import org.example.repository.GenresRepository;

@Component
public class StringToGenreConverter implements Converter<String, GenresEntity> {

    private final GenresRepository genresRepository;

    @Autowired
    public StringToGenreConverter(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    @Override
    public GenresEntity convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        try {
            Long id = Long.parseLong(source);
            return genresRepository.findById(id).orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

