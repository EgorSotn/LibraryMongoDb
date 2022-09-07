package org.example.home.repository.book;

import lombok.val;
import org.example.home.domain.Author;
import org.example.home.domain.Book;
import org.example.home.domain.Genre;
import org.example.home.repository.AbstractRepositoryMongoTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class BookRepositoryCustomImplTest extends AbstractRepositoryMongoTest {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    BookRepository bookRepository;

    @Test
    void shouldExistBookToReturnBool(){
        val genre = Collections.singletonList( new Genre("prosa"));
        val author = new Author("Lermonov", "1812-01-01");
        val book = new Book("War and Peace", "1834-01-01", genre, author, null);
        val isNotExist = bookRepository.isExistBook(book);
        assertFalse(isNotExist);
        val saveBook = mongoTemplate.save(book);

        val isExist = bookRepository.isExistBook(saveBook);
        assertTrue(isExist);
    }
}