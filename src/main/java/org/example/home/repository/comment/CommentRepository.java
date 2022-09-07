package org.example.home.repository.comment;

import org.example.home.domain.Book;
import org.example.home.domain.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CommentRepository extends MongoRepository<Comment, String>,CommentRepositoryCustom {
//    @EntityGraph(attributePaths = {"book"})
//    @Override
//    List<Comment> findAll();
}
