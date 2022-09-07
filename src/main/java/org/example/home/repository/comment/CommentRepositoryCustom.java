package org.example.home.repository.comment;

import org.example.home.domain.Comment;
import org.example.home.domain.Genre;

import java.util.Optional;

public interface CommentRepositoryCustom {
    Optional<Comment> getByNameOrCreate(Comment comment);
}
