package org.example.home.dao;


import org.example.home.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {
    Comment save(Comment comment);
    void deleteById(long id);
    void updateTextById(long id, String  text);
    Optional<Comment> getById(long id);
    List<Comment> getAll();

}
