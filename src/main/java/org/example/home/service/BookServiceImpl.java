package org.example.home.service;

import lombok.AllArgsConstructor;
import org.example.home.domain.Book;
import org.example.home.domain.Genre;
import org.example.home.exception.NotFoundException;
import org.example.home.repository.author.AuthorRepository;
import org.example.home.repository.book.BookRepository;
import org.example.home.repository.genre.GenreRepository;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{
    private BookRepository bookRepository;
    private GenreRepository genreRepository;
    private AuthorRepository authorRepository;
    @Override
    public Book createBook(Book book) {
        List<Genre> genres = book.getGenres();
        for (Genre genre : genres) {
            genreRepository.getByNameOrCreate(genre);
        }
        authorRepository.getByNameOrCreate(book.getAuthor());
        if (bookRepository.isExistBook(book)) {
            return null;
        }

         return bookRepository.save(book);
    }

    @Override
    public void deleteBook(String id) throws NotFoundException {
        Book deleteBook = bookRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        bookRepository.delete(deleteBook);
    }

    @Override
    public Book updateBook(Book updateBook)throws NotFoundException {
        Book book = bookRepository.findById(updateBook.getId()).orElseThrow(()-> new NotFoundException(updateBook.getId()));
        book.setName(updateBook.getName());
        book.setYear(updateBook.getYear());
        book.setGenres(updateBook.getGenres());
        book.setAuthor(updateBook.getAuthor());


        return bookRepository.save(book);

    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public Book getById(String id) throws NotFoundException{
        return bookRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }
}
