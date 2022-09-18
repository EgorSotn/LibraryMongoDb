package org.example.home.service;


import org.example.home.controller.ControllerMessenger;

import org.example.home.domain.Author;
import org.example.home.domain.Genre;
import org.example.home.repository.author.AuthorRepository;
import org.example.home.repository.book.BookRepository;
import org.example.home.repository.comment.CommentRepository;
import org.example.home.repository.genre.GenreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
class LibraryServiceImplTest{
    private static final String INSERT_ID_AUTHOR = "1";
    private static final String INSERT_NAME_BOOK = "evangelion";
    private static final String INSERT_YEAR_BOOK = "1999-05-11";
    private static final String INSERT_NAME_AUTHOR = "Pushkin";
    private static final String INSERT_YEAR_AUTHOR = "1993-05-11";


    private static final String INSERT_ID_GENRE1 = "1";
    private static final String INSERT_ID_GENRE2 = "2";
    private static final String INSERT_NAME_GENRE1 = "drama";
    private static final String INSERT_NAME_GENRE2 = "horror";

    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private GenreRepository genreRepository;
    @MockBean
    private CommentRepository commentRepository;
    @MockBean
    private ControllerMessenger controllerMessenger;

    @Autowired
    private LibraryService libraryService;

    @DisplayName("ожидается, что книга не добавиться бд")
    @Test
    void shouldDontSaveBook() {

        when(authorRepository.getByNameOrCreate(any())).thenReturn(
                Optional.of(new Author(INSERT_ID_AUTHOR,INSERT_NAME_AUTHOR, INSERT_YEAR_AUTHOR)));

        when(genreRepository.getByNameOrCreate(any())).thenReturn(
                Optional.of( new Genre(INSERT_ID_GENRE1, INSERT_NAME_GENRE1)));
        when(genreRepository.getByNameOrCreate(any())).thenReturn(
                Optional.of(new Genre(INSERT_ID_GENRE2, INSERT_NAME_GENRE2)));
//        when(bookRepository.isExistBook(any())).thenReturn(true);
        when(controllerMessenger.getAsk()).thenReturn(INSERT_NAME_BOOK)
                .thenReturn(INSERT_YEAR_BOOK)
                .thenReturn(INSERT_NAME_AUTHOR)
                .thenReturn(INSERT_YEAR_AUTHOR)
                .thenReturn(INSERT_NAME_GENRE1)
                .thenReturn(INSERT_NAME_GENRE2);

        when(bookRepository.isExistBook(any())).thenReturn(true);

        final boolean isInsert = libraryService.addBookOrReturnBool();

        verify(bookRepository, times(1)).isExistBook(any());
        verify(bookRepository, times(0)).save(any());

        assertThat(isInsert).isFalse();
    }

    @DisplayName("ожидается, что книга добавиться бд")
    @Test
    void shouldSaveBook() {
        when(authorRepository.getByNameOrCreate(any())).thenReturn(
               Optional.of( new Author(INSERT_ID_AUTHOR,INSERT_NAME_AUTHOR, INSERT_YEAR_AUTHOR)));

        when(genreRepository.getByNameOrCreate(any())).thenReturn(
                Optional.of( new Genre(INSERT_ID_GENRE1, INSERT_NAME_GENRE1)));
        when(genreRepository.getByNameOrCreate(any())).thenReturn(
                Optional.of(  new Genre(INSERT_ID_GENRE2, INSERT_NAME_GENRE2)));

        when(controllerMessenger.getAsk()).thenReturn(INSERT_NAME_BOOK)
                .thenReturn(INSERT_YEAR_BOOK)
                .thenReturn(INSERT_NAME_AUTHOR)
                .thenReturn(INSERT_YEAR_AUTHOR)
                .thenReturn(INSERT_NAME_GENRE1)
                .thenReturn(INSERT_NAME_GENRE2);

        when(bookRepository.isExistBook(any())).thenReturn(false);

        final boolean isInsert = libraryService.addBookOrReturnBool();

        verify(bookRepository, times(1)).isExistBook(any());
        verify(bookRepository, times(1)).save(any());

        assertThat(isInsert).isTrue();
    }


}