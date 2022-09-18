package org.example.home.repository.genre;

import org.example.home.domain.Genre;

import java.util.Optional;

public interface GenreRepositoryCustom {
    Optional<Genre> getByNameOrCreate(Genre genre);
}
