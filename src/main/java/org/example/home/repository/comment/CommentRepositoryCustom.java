package org.example.home.repository.comment;

import org.example.home.domain.Comment;
import org.example.home.domain.Genre;

public interface CommentRepositoryCustom {
    Comment getByNameOrCreate(Comment comment);
}
