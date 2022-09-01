package org.example.home.dao.jpa.hibernate.author;

import lombok.val;
import org.example.home.domain.Author;
import org.example.home.domain.Genre;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test author dao")
@DataJpaTest
@ComponentScan("main.org.example.home.dao.jpa")
@Import(AuthorDaoJpa.class)
class AuthorDaoJpaTest {
    private static final long FIRST_AUTHOR_ID = 1l;
    private static final String AUTHOR_NAME = "Вася";
    private static final long EXPECTED_QUERIES_COUNT = 1;
    @Autowired
    AuthorDaoJpa authorDaoJpa;

    @Autowired
    TestEntityManager em;

    @DisplayName("должен корректно сохранять всю информацию о авторе")
    @Test
    void shouldSaveGenre() {
        val author = new Author(2,"AAAAAA", "1975-06-22");
        authorDaoJpa.save(author);
        assertThat(author.getIdAuthor()).isGreaterThan(0);
        val actualAuthor = em.find(Author.class, author.getIdAuthor());

        assertThat(actualAuthor).isNotNull().matches(a -> !a.getNameAuthor().equals(""))
                .matches(a-> !a.getYear().equals(""));
    }

    @DisplayName("должен удалять заданного автора по его id")
    @Test
    void shouldDeleteGenre() {
        val author = em.find(Author.class, FIRST_AUTHOR_ID);
        assertThat(author).isNotNull();
        em.detach(author);
        authorDaoJpa.deleteById(FIRST_AUTHOR_ID);
        val deleteAuthor = em.find(Author.class,FIRST_AUTHOR_ID);

        assertThat(deleteAuthor).isNull();
    }

    @DisplayName("должен изменять имя заданного автора по его id")
    @Test
    void shouldUpdateNameGenreById() {
        val firstAuthor = em.find(Genre.class, FIRST_AUTHOR_ID);
        val oldName = firstAuthor.getNameGenre();
        em.detach(firstAuthor);
        authorDaoJpa.updateNameById(FIRST_AUTHOR_ID, AUTHOR_NAME);
        val updateAuthor = em.find(Author.class, FIRST_AUTHOR_ID);
        assertThat(updateAuthor.getNameAuthor()).isNotEqualTo(oldName).isEqualTo(AUTHOR_NAME);
    }

    @DisplayName("должен загружать информацию о нужном авторе по его id")
    @Test
    void shouldReturnGenreGetById() {
        val optionalActualAuthor = authorDaoJpa.getById(FIRST_AUTHOR_ID);
        val expectedAuthor = em.find(Author.class, FIRST_AUTHOR_ID);
        assertThat(optionalActualAuthor).isPresent().get()
                .isEqualTo(expectedAuthor);
    }

    @DisplayName("должен загружать список всех авторов с полной информацией о них")
    @Test
    void shouldReturnAllGenre() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        val authors  = authorDaoJpa.getAll();
        assertThat(authors.size()).isEqualTo(1);

        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }
}