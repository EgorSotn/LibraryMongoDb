package org.example.home.repository.author;

import lombok.RequiredArgsConstructor;
import org.example.home.domain.Author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom{
//    @PersistenceContext
//    private EntityManager em;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Optional< Author> getByNameOrCreate(Author author) {
//        TypedQuery<Author> query = em.createQuery("Select a from Author a WHERE a.nameAuthor = :name", Author.class);
//        query.setParameter("name", author.getNameAuthor());
//        List<Author> authors = query.getResultList();
//
//

        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(author.getNameAuthor()));
        List<Author> authors = mongoTemplate.find(query,Author.class);
        if (!authors.isEmpty()) {
            return Optional.of( authors.get(0));
        } else {
            return Optional.of( mongoTemplate.save(author));
        }
    }
}
