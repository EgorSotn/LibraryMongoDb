package org.example.home.dao;


import org.example.home.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    Genre save(Genre genre);
    void deleteById(long id);
    void updateNameById(long id, String  name);
    Optional<Genre> getById(long id);
    List<Genre> getAll();
    Object getByNameOrCreate(Genre genre);
}
