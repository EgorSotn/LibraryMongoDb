package org.example.home.service;


import org.example.home.domain.Book;

import java.util.List;

public interface BookService {
    Book createBook(Book book);
    void deleteBook(String id);
    Book updateBook(Book updateBook);
    List<Book> getAllBook();
    Book getById(String id);
}
