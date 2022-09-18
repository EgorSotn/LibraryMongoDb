package org.example.home.repository.genre;

import org.example.home.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;



public class GenreRepositoryCustomImpl implements GenreRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Optional<Genre> getByNameOrCreate(Genre genre) {
//        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g WHERE g.nameGenre = :name", Genre.class);
//        query.setParameter("name", genre.getNameGenre());
//        List<Genre> genres = query.getResultList();
//        if(!genres.isEmpty()){
//            return Optional.of(genres.get(0));
//        }
//        else {
//            return Optional.of(em.merge(genre));
//        }
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(genre.getNameGenre()));
        List<Genre> genres = mongoTemplate.find(query, Genre.class);
        if (!genres.isEmpty()) {
            return Optional.of(genres.get(0));
        } else {
            return Optional.of(mongoTemplate.save(genre));
        }
    }
}
