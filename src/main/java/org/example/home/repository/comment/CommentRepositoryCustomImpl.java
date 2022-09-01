package org.example.home.repository.comment;

import org.example.home.domain.Comment;
import org.example.home.domain.Genre;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


public class CommentRepositoryCustomImpl implements CommentRepositoryCustom{
    @PersistenceContext
    EntityManager em;



    @Override
    public Comment getByNameOrCreate(Comment comment) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.text = :text", Comment.class);
        query.setParameter("text", comment.getText());
        List<Comment> comments = query.getResultList();
        if(!comments.isEmpty()){
            return comments.get(0);
        }
        else {
            return em.merge(comment);
        }

    }
}
