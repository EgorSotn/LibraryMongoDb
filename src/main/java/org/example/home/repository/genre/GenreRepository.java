package org.example.home.repository.genre;

import org.example.home.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface GenreRepository extends MongoRepository<Genre, String>, GenreRepositoryCustom {
}