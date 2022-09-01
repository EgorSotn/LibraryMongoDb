package org.example.home.dao.jpa.hibernate.comment;

import org.example.home.dao.CommentDao;
import org.example.home.domain.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@Repository
public class CommentDaoJpa implements CommentDao {
    @PersistenceContext
    EntityManager em;
    @Override
    public Comment save(Comment comment) {
        if(comment.getId_comment() == 0){
            em.persist(comment);
            return comment;
        }
        else {
           return em.merge(comment);
        }

    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Comment c where c.id_comment = :id ");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateTextById(long id, String text) {
        Query query = em.createQuery("UPDATE Comment c SET c.text = :text WHERE c.id_comment =:id");
        query.setParameter("text", text);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Optional<Comment> getById(long id) {
//        TypedQuery<Comment> query = em.createQuery("select c from Comment c WHERE c.idComment = :id", Comment.class);
//        query.setParameter("id", id);
//        return query.getSingleResult();
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> getAll() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c LEFT JOIN FETCH c.book ", Comment.class);
        return query.getResultList();
    }
}
