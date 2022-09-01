package org.example.home.repository.author;

import org.example.home.domain.Author;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom{
    @PersistenceContext
    EntityManager em;



    @Override
    public Optional< Author> getByNameOrCreate(Author author) {
        TypedQuery<Author> query = em.createQuery("Select a from Author a WHERE a.nameAuthor = :name", Author.class);
        query.setParameter("name", author.getNameAuthor());
        List<Author> authors = query.getResultList();

        if (!authors.isEmpty()) {
            return Optional.of( authors.get(0));
        } else {
            return Optional.of( em.merge(author));
        }
    }
}
