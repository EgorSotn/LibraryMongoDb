package org.example.home.dao.jpa.hibernate.genre;

import lombok.val;
import org.example.home.domain.Genre;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test genre dao")
@DataJpaTest
@Import(GenreDaoJpa.class)
class GenreDaoJpaTest {
    private static final long FIRST_GENRE_ID = 1l;
    private static final String GENRE_NAME = "Вася";
    private static final long EXPECTED_QUERIES_COUNT = 1;
    @Autowired
    GenreDaoJpa genreDaoJpa;

    @Autowired
    TestEntityManager em;

    @DisplayName("должен корректно сохранять всю информацию о жанре")
    @Test
    void shouldSaveGenre() {
        val genre = new Genre(2, "dsfvvvxcxv");
        genreDaoJpa.save(genre);
        assertThat(genre.getIdGenre()).isGreaterThan(0);

        val actualGenre = em.find(Genre.class, genre.getIdGenre());

        assertThat(actualGenre).isNotNull().matches(c -> !c.getNameGenre().equals(""));
    }

    @DisplayName("должен удалять заданного жанра по его id")
    @Test
    void shouldDeleteGenre() {
        val firstGenre = em.find(Genre.class, FIRST_GENRE_ID);
        assertThat(firstGenre).isNotNull();
        em.detach(firstGenre);
        genreDaoJpa.deleteById(FIRST_GENRE_ID);
        val deleteGenre = em.find(Genre.class, FIRST_GENRE_ID);
        assertThat(deleteGenre).isNull();
    }

    @DisplayName("должен изменять имя заданного жанра по его id")
    @Test
    void shouldUpdateNameGenreById() {
        val firstGenre = em.find(Genre.class, FIRST_GENRE_ID);
        val oldName = firstGenre.getNameGenre();
        em.detach(firstGenre);
        genreDaoJpa.updateNameById(FIRST_GENRE_ID, GENRE_NAME);
        val updateGenre = em.find(Genre.class, FIRST_GENRE_ID);
        assertThat(updateGenre.getNameGenre()).isNotEqualTo(oldName).isEqualTo(GENRE_NAME);
    }

    @DisplayName("должен загружать информацию о нужном жанре по его id")
    @Test
    void shouldReturnGenreGetById() {
        val optionalActualGenre = genreDaoJpa.getById(FIRST_GENRE_ID);
        val expectedGenre = em.find(Genre.class, FIRST_GENRE_ID);
        assertThat(optionalActualGenre).isPresent().get()
                .isEqualTo(expectedGenre);
    }

    @DisplayName("должен загружать список всех жанров с полной информацией о них")
    @Test
    void shouldReturnAllGenre() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        val genres  = genreDaoJpa.getAll();
        assertThat(genres.size()).isEqualTo(2);

        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }
}