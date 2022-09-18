package org.example.home.repository.author;

import org.example.home.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface AuthorRepository extends MongoRepository<Author,String>, AuthorRepositoryCustom {
}