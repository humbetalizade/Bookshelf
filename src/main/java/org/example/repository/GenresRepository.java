package org.example.repository;

import org.example.entity.GenresEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenresRepository extends JpaRepository<GenresEntity, Long> {
}
