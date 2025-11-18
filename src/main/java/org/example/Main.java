package org.example;

import org.example.entity.GenresEntity;
import org.example.repository.GenresRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner initGenres(GenresRepository genreRepository) {
        return args -> {
            if (genreRepository.count() == 0) {
                genreRepository.save(new GenresEntity("Elmi-fantastik"));
                genreRepository.save(new GenresEntity("Romantika"));
                genreRepository.save(new GenresEntity("Detektiv"));
                genreRepository.save(new GenresEntity("Şəxsi inkişaf"));
                genreRepository.save(new GenresEntity("Dərslik"));
                genreRepository.save(new GenresEntity("Ədəbiyyat"));
            }
        };
    }

}
