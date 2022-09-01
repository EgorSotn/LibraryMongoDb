package org.example.home.repository.genre;

import org.example.home.domain.Genre;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


public class GenreRepositoryCustomImpl implements GenreRepositoryCustom{
    @PersistenceContext
    EntityManager em;



    @Override
    public Optional< Genre> getByNameOrCreate(Genre genre) {
        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g WHERE g.nameGenre = :name", Genre.class);
        query.setParameter("name", genre.getNameGenre());
        List<Genre> genres = query.getResultList();
        if(!genres.isEmpty()){
            return Optional.of(genres.get(0));
        }
        else {
            return Optional.of(em.merge(genre));
        }
    }
}
