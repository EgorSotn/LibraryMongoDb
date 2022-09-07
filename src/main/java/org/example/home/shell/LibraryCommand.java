package org.example.home.shell;


import lombok.AllArgsConstructor;
import org.example.home.domain.Author;
import org.example.home.domain.Book;
import org.example.home.domain.Genre;
import org.example.home.service.LibraryService;
import org.example.home.service.PresentationService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


@ShellComponent
@Repository
@AllArgsConstructor
public class LibraryCommand {
    private LibraryService libraryService;
    private PresentationService presentationService;



    @ShellMethod(value = "print book", key = "printAll")
    public String printBooks(){
        List<Book> books = getAllBook();
    //    return books.stream().map(Book::toString).collect(Collectors.joining("\n"));
        return presentationService.convertBookForShell(books);
    }
    @ShellMethod(value = "add book", key = "addBook")
    public String addNewBook(){
        return libraryService.addBookOrReturnBool() ? "successfully" : "fail: maybe this book is already exist";
     //   return presentationService.convertBookForShell();
    }
    @ShellMethod(key = "authors", value = "list all author")
    public String listAuthors() {
        return libraryService.getAllAuthor().stream().map(Author::toString).collect(Collectors.joining(", "));
    }

    @ShellMethod(key = "genres", value = "list all genres")
    public String listStyle() {
        return libraryService.getAllGenre().stream().map(Genre::toString).collect(Collectors.joining(", "));
    }
    @ShellMethod(key = "addGenre", value = "add new genre")
    public String addNewStyle(String name) {
        return libraryService.getGenreOrCreate(name).toString();
    }
    @ShellMethod(key = "addAuthor", value = "add new author")
    public String addNewAuthor(String name, String year) {
        return libraryService.getAuthorOrCreate(name, year).toString();
    }

    private List<Book> getAllBook(){
        return libraryService.getAllBook();
    }
}
