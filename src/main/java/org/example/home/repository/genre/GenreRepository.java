package org.example.home.repository.genre;

import org.example.home.domain.Book;
import org.example.home.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface GenreRepository extends MongoRepository<Genre, String>, GenreRepositoryCustom {
}