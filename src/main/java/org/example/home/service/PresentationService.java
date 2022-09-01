package org.example.home.service;

import org.example.home.domain.Author;
import org.example.home.domain.Book;
import org.example.home.domain.Genre;

import java.util.List;

public interface PresentationService {
    String convertBookForShell(List<Book> books);
    String convertAuthorForShell(List<Author> authors);
    String convertGenreForShell(List<Genre> genres);
}
