package org.example.repository;


import org.example.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository  extends JpaRepository<BookEntity,Long> {

    List<BookEntity> findByTitleContainingIgnoreCase(String keyword);
    boolean existsByTitle(String title);



}



