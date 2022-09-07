package org.example.home.mongock.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import lombok.Data;
import lombok.val;
import org.example.home.domain.Author;
import org.example.home.domain.Book;
import org.example.home.domain.Comment;
import org.example.home.domain.Genre;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Data
@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {
    private Author author;
    private List<Genre> genre;
    private List<Comment> comment;

    @ChangeSet(order = "000", id = "dropDB", author = "egor", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }
    @ChangeSet(order = "001", id = "initAuthor", author = "egor", runAlways = true)
    public void initAuthors(MongoTemplate template){

        author = new Author("Duma", "1784-01-01");
        template.save(author);
    }

    @ChangeSet(order = "002", id = "initGenre", author = "egor", runAlways = true)
    public void initGenres(MongoTemplate template){
        genre = Collections.singletonList( new Genre("prosa"));
        template.save(genre);
    }

    @ChangeSet(order = "003", id = "initComment", author = "egor", runAlways = true)
    public void initComments(MongoTemplate template){
        comment = Collections.singletonList(new Comment("comment"));
        template.save(comment);
    }
    @ChangeSet(order = "003", id = "initBook", author = "egor", runAlways = true)
    public void initBooks(MongoTemplate template){
        val book = new Book("War and Peace", "1834-01-01", genre, author, comment);
    }
}
