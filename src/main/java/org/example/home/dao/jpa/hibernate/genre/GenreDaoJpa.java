package org.example.home.dao.jpa.hibernate.genre;


import org.example.home.dao.GenreDao;
import org.example.home.domain.Genre;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;



@Repository
public class GenreDaoJpa implements GenreDao {
    @PersistenceContext
    EntityManager em;
    @Override
    public Genre save(Genre genre) {
//        namedParameterJdbcOperations.update("INSERT INTO genre(id_genre, name) VALUES(:id_genre, :name)",
//                Map.of("id_genre", genre.getIdGenre(), "name", genre.getNameGenre()));
        if(genre.getIdGenre() == 0){
            em.persist(genre);
            return genre;
        }else {
            return em.merge(genre);
        }
    }

    @Override
    public void deleteById(long id) {
//        namedParameterJdbcOperations.update("DELETE FROM genre WHERE id_genre = :id", Map.of("id", genre.getIdGenre()));
        Query query = em.createQuery("delete from Genre g where g.idGenre = :id ");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateNameById(long id, String  name) {
//        namedParameterJdbcOperations.update("UPDATE genre SET name = :name WHERE id_genre = :id",
//                Map.of("name", name, "id", genre.getIdGenre()));
        Query query = em.createQuery("update Genre g " +
                "set g.nameGenre = :name " +
                "WHERE g.idGenre = :id");
        query.setParameter("name", name);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Optional<Genre> getById(long id) {
//        Genre genre = null;
//        try {
//            genre =  namedParameterJdbcOperations.queryForObject("select * from genre where id_genre =:id",
//                    Map.of("id", id), new GenreMapper());
//        }catch (EmptyResultDataAccessException e){
//            return null;
//        }
//        return genre;
//        TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.idGenre = :id", Genre.class);
//        query.setParameter("id", id);
//        return query.getSingleResult();
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public List<Genre> getAll() {
//        return namedParameterJdbcOperations.query("select * from genre", new GenreMapper());
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public Genre getByNameOrCreate(Genre genre) {
        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g WHERE g.nameGenre = :name", Genre.class);
        query.setParameter("name", genre.getNameGenre());
        List<Genre> genres = query.getResultList();
        if(!genres.isEmpty()){
            return genres.get(0);
        }
        else {
            return em.merge(genre);
        }
    }
}
