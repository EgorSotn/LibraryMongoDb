package org.example.home.dao;

import org.example.home.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book save(Book book);
    void deleteById(long id);
    void updateNamedById(long id, String  name);
    Optional<Book> getById(long id);
    //List<Book> getAllNotProb();
    boolean isExistBook(Book book);
    List<Book> getAll();
}
