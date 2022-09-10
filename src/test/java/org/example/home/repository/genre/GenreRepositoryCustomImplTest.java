package org.example.home.repository.genre;

import lombok.val;
import org.example.home.domain.Author;
import org.example.home.domain.Genre;
import org.example.home.repository.AbstractRepositoryMongoTest;
import org.example.home.repository.author.AuthorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class GenreRepositoryCustomImplTest extends AbstractRepositoryMongoTest {
//    private static final long FIRST_GENRE_ID = 1l;
//    @Autowired
//    private TestEntityManager em;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private GenreRepository genreRepository;

    @DisplayName("должен добавлять в бд жанра или возвращать по его ")
    @Test
    void getByNameOrCreate() {
        val genre = mongoTemplate.save(new Genre("prosa"));
        val genres = genreRepository.findAll();
        val firstGenre = genres.get(0);
        val actualGenre  = genreRepository.getByNameOrCreate(genre).get();
        //               authorRepository.getByNameOrCreate(firstAuthor);
        assertThat(actualGenre).isEqualTo(firstGenre);


        val newGenre = new Genre("aaaa" );
        val actualCommentElse = genreRepository.getByNameOrCreate(newGenre);
        assertThat(actualCommentElse).get().isEqualTo(newGenre);

//        val firstGenre = em.find(Genre.class,FIRST_GENRE_ID );
//        val actualGenre =  genreRepository.getByNameOrCreate(firstGenre);
//
//        assertThat(actualGenre).isEqualTo(firstGenre);
//
//
//        val newGenre = new Genre("3","aaaaaaa");
//        val actualGenreElse = genreRepository.getByNameOrCreate(newGenre);
//        assertThat(actualGenreElse).isEqualTo(newGenre);
    }
}