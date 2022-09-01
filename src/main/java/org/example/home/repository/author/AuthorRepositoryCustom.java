package org.example.home.repository.author;

import org.example.home.domain.Author;

import java.util.Optional;

public interface AuthorRepositoryCustom {
    Optional< Author> getByNameOrCreate(Author author);
}
