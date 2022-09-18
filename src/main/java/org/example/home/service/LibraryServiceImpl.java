package org.example.home.service;

import lombok.AllArgsConstructor;
import org.example.home.controller.ControllerMessenger;


import org.example.home.domain.Author;
import org.example.home.domain.Book;
import org.example.home.domain.Genre;
import org.example.home.repository.author.AuthorRepository;
import org.example.home.repository.book.BookRepository;

import org.example.home.repository.genre.GenreRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class LibraryServiceImpl implements LibraryService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;


    private ControllerMessenger controllerMessenger;


    @Override
    public boolean addBookOrReturnBool() {

        controllerMessenger.textMessage("Введите название книги: ");
        String nameBook = controllerMessenger.getAsk();

        controllerMessenger.textMessage("Введите год выпуска книги: ");
        String dateBook = controllerMessenger.getAsk();

        controllerMessenger.textMessage("Введите псевдоним автора книги: ");
        String nameAuthor = controllerMessenger.getAsk();

        controllerMessenger.textMessage("Введите дату рождения автора книги: ");
        String yearAuthor = controllerMessenger.getAsk();

        controllerMessenger.textMessage("Введите жанры книге через пробел или запятую: ");
        String[] genresName = controllerMessenger.getAsk().split(",");
        List<Genre> genreList = new ArrayList<>();
        for (int i=0; i<genresName.length; i++){
            Genre genre = (Genre) genreRepository.getByNameOrCreate(new Genre( genresName[i])).get();
            genreList.add(genre);
        }

        Author author = authorRepository.getByNameOrCreate(new Author(nameAuthor, yearAuthor)).get();

        Book insertBook = new Book(nameBook,   dateBook, genreList, author, null);

        if (bookRepository.isExistBook(insertBook)) {
            return false;
        }
        bookRepository.save(insertBook);

        return true;

    }

    @Override
    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }

    @Override
    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> getGenreOrCreate(String name) {
        return genreRepository.getByNameOrCreate(new Genre(name));
    }

    @Override
    public Optional<Author> getAuthorOrCreate(String name, String year) {
        return authorRepository.getByNameOrCreate(new Author(name, year));
    }


    @Override
    public List<Book> getAllBook() {

        return bookRepository.findAll();
    }



//    @Transactional
//    @Override
//    public void updateNameBook() {
//        controllerMessenger.textMessage("Введите id книги: ");
//        long idBook = Long.parseLong(controllerMessenger.getAsk());
//        controllerMessenger.textMessage("Введите название книги: ");
//        String nameBook = controllerMessenger.getAsk();
//        bookRepository.updateNameById(nameBook, idBook);
//    }
//
//    @Transactional
//    @Override
//    public void getByIdBook() {
//        controllerMessenger.textMessage("Введите id книги: ");
//        long idBook = Long.parseLong(controllerMessenger.getAsk());
//        System.out.println( bookRepository.getById(idBook));
//
//    }
//    @Transactional
//    @Override
//    public void deleteByIdBook() {
//        controllerMessenger.textMessage("Введите id книги: ");
//        long idBook = Long.parseLong(controllerMessenger.getAsk());
//        bookRepository.deleteById(idBook);
//    }
}
