package org.example.home.service;

import org.example.home.domain.Author;
import org.example.home.domain.Book;
import org.example.home.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface LibraryService {
    boolean addBookOrReturnBool();
    List<Author> getAllAuthor();
    List<Genre> getAllGenre();
    Optional<Genre> getGenreOrCreate(String name);
    Optional<Author> getAuthorOrCreate(String name, String year);
    public List<Book> getAllBook();
//    void updateNameBook();
//    void getByIdBook();
//    void deleteByIdBook();
}
