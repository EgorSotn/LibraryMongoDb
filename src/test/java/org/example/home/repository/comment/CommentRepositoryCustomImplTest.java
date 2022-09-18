package org.example.home.repository.comment;

import lombok.val;
import org.example.home.domain.Comment;
import org.example.home.repository.AbstractRepositoryMongoTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
class CommentRepositoryCustomImplTest extends AbstractRepositoryMongoTest {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("должен добавлять в бд комментарии или возвращать по его")
    @Test
    void getByNameOrCreate() {
        val comment = mongoTemplate.save(new Comment("adsad"));
        val comments = commentRepository.findAll();
        val firstComment = comments.get(0);
        val actualComment  = commentRepository.getByNameOrCreate(comment).get();
        //               authorRepository.getByNameOrCreate(firstAuthor);
        assertThat(actualComment).isEqualTo(firstComment);


        val newComment = new Comment("asfdfgh" );
        val actualCommentElse = commentRepository.getByNameOrCreate(newComment);
        assertThat(actualCommentElse).get().isEqualTo(newComment);
//        Comment firstComment = em.find(Comment.class,FIRST_COMMENT_ID );
//        Comment actualComment =  commentRepository.getByNameOrCreate(firstComment);
//
//        assertThat(actualComment).isEqualTo(firstComment);
//
//
//        Comment newComment = new Comment("2","aaaaa");
//        Comment actualCommentElse = commentRepository.getByNameOrCreate(newComment);
//        assertThat(actualCommentElse).isEqualTo(newComment);
    }
}