package org.example.home.repository.comment;

import org.example.home.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CommentRepository extends MongoRepository<Comment, String>,CommentRepositoryCustom {
//    @EntityGraph(attributePaths = {"book"})
//    @Override
//    List<Comment> findAll();
}
