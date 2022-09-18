package org.example.home.repository.comment;

import org.example.home.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;



public class CommentRepositoryCustomImpl implements CommentRepositoryCustom{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Optional<Comment> getByNameOrCreate(Comment comment) {
//        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.text = :text", Comment.class);
//        query.setParameter("text", comment.getText());
//        List<Comment> comments = query.getResultList();
//        if(!comments.isEmpty()){
//            return comments.get(0);
//        }
//        else {
//            return em.merge(comment);
//        }
        Query query = new Query();
        query.addCriteria(Criteria.where("text").is(comment.getText()));
        List<Comment> comments = mongoTemplate.find(query, Comment.class);
        if (!comments.isEmpty()) {
            return Optional.of(comments.get(0));
        } else {
            return Optional.of(mongoTemplate.save(comment));
        }
    }
}
