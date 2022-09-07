package org.example.home.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

import java.util.Collections;
import java.util.List;


@Document("books")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Book {

    @Id
    private String idBook;


    @Field("name")
    private String name;


    @Field("year")
    private String year;


    @Field("genre")
    private List<Genre> genres;


    @Field("author")
    private Author author;

    @DBRef
    private List<Comment> comments;

    public Book(String idBook, String name, String year, Genre genre, Author author, Comment comments) {
        this.idBook = idBook;
        this.name = name;
        this.year = year;
        this.genres = Collections.singletonList(genre);
        this.author = author;
        this.comments = Collections.singletonList(comments);
    }

    public Book(String idBook, String name, String year, Genre genre, Author author) {
        this.idBook = idBook;
        this.name = name;
        this.year = year;
        this.genres = Collections.singletonList(genre);
        this.author = author;
    }

    public Book(String name, String year, List<Genre> genres, Author author, List<Comment> comments) {
        this.name = name;
        this.year = year;
        this.genres = genres;
        this.author = author;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Book{" +
                "idBook=" + idBook +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", genres=" + genres +
                ", author=" + author +
                '}';
    }
}
