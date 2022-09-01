package org.example.home.dao;

import org.example.home.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Author save(Author author);
    void deleteById(long id);
    void updateNameById(long id, String  name);
    Optional<Author> getById(long id);
    List<Author> getAll();
    Author getByNameOrCreate(Author author);
}

