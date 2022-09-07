package org.example.home.service;

import lombok.AllArgsConstructor;
import org.example.home.controller.ControllerMessengerIO;
import org.example.home.domain.Author;
import org.example.home.domain.Book;
import org.example.home.domain.Comment;
import org.example.home.domain.Genre;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PresentationServiceImpl implements PresentationService{
  //  private ControllerMessengerIO controllerMessengerIO;
    @Override
    public String convertBookForShell(List<Book> books) {
         return books.stream().map(this::convertBookToString).collect(Collectors.joining("\n"));
    }

    @Override
    public String convertAuthorForShell(List<Author> authors) {
        return authors.stream().map(Author::toString).collect(Collectors.joining(", "));
    }

    @Override
    public String convertGenreForShell(List<Genre> genres) {
        return genres.stream().map(Genre::toString).collect(Collectors.joining(", "));
    }

    private String convertBookToString(Book book){
        String str = "(" + "id=:" + book.getIdBook() + " Название: " + book.getName() + " Год издания: " + book.getYear()
                + " Имя автора: "  + book.getAuthor().getNameAuthor() +
                " Название жанра: "
                + book.getGenres().stream().map(Genre::toString).collect(Collectors.joining(",")) + " ";
       return str;
    }
}
