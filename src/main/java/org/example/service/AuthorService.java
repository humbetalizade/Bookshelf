package org.example.service;


import org.example.dto.AuthorDto;
import org.example.entity.AuthorEntity;
import org.example.mapper.AuthorMapper;
import org.example.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthorService implements AuthorServiceInterface {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Set<AuthorDto> getAllAuthors(){
        Set<AuthorDto> authors = new LinkedHashSet<>();
        List<AuthorEntity> authorEntities = authorRepository.findAll();
        for(AuthorEntity author: authorEntities){
            authors.add(AuthorMapper.toDto(author));
        }
        return authors;
    }


    @Override
    public void deleteBook(Long id) {
        System.out.println("DELETE ID = " + id);
        authorRepository.deleteById(id);
    }

}
