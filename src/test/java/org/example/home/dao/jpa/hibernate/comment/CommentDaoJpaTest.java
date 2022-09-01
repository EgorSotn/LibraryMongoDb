package org.example.home.dao.jpa.hibernate.comment;

import lombok.val;
import org.example.home.domain.Comment;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test Comment dao")
@DataJpaTest
@Import({CommentDaoJpa.class})
class CommentDaoJpaTest {
    private static final long FIRST_COMMENT_ID = 1l;
    private static final String COMMENT_TEXT = "Вася";
    private static final long EXPECTED_QUERIES_COUNT = 2;
    @Autowired
    CommentDaoJpa commentDaoJpa;

    @Autowired
    TestEntityManager em;

    @DisplayName("должен корректно сохранять всю информацию о комментарии")
    @Test
    void shouldSaveComment() {
        val comment = new Comment(2, "dsfvvvxcxv");
        commentDaoJpa.save(comment);
        assertThat(comment.getId_comment()).isGreaterThan(0);

        val actualComment = em.find(Comment.class, comment.getId_comment());

        assertThat(actualComment).isNotNull().matches(c -> !c.getText().equals(""));

    }

    @DisplayName("должен удалять заданного комментарии по его id")
    @Test
    void shouldDeleteComment() {
        val firstComment = em.find(Comment.class, FIRST_COMMENT_ID);
        assertThat(firstComment).isNotNull();
        em.detach(firstComment);

        commentDaoJpa.deleteById(FIRST_COMMENT_ID);
        val deletedComment = em.find(Comment.class, FIRST_COMMENT_ID);
        assertThat(deletedComment).isNull();
    }

    @DisplayName("должен изменять имя заданного комментарии по его id")
    @Test
    void shouldUpdateTextById() {
        val firstComment = em.find(Comment.class, FIRST_COMMENT_ID);
        val oldText = firstComment.getText();
        em.detach(firstComment);

        commentDaoJpa.updateTextById(FIRST_COMMENT_ID, COMMENT_TEXT);
        Comment updateTextComment = em.find(Comment.class, FIRST_COMMENT_ID);

        assertThat(updateTextComment.getText()).isNotEqualTo(oldText).isEqualTo(COMMENT_TEXT);
    }

    @DisplayName("должен загружать информацию о нужном комментарии по его id")
    @Test
    void shouldReturnCommentGetById() {
        val optionalActualComment = commentDaoJpa.getById(FIRST_COMMENT_ID);
        val expectedComment = em.find(Comment.class, FIRST_COMMENT_ID);
        assertThat(optionalActualComment).isPresent().get()
                .isEqualTo(expectedComment);
    }

    @DisplayName("должен загружать список всех комментариев с полной информацией о них")
    @Test
    void shouldReturnAllComment() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        val comments  = commentDaoJpa.getAll();
        assertThat(comments.size()).isEqualTo(1);

        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }
}