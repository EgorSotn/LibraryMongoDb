package org.example.home.dao.jpa.hibernate.author;



import org.example.home.dao.AuthorDao;
import org.example.home.domain.Author;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;



@Repository
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Author save(Author author) {
        if(author.getIdAuthor()<=0){
            em.persist(author);
            return author;
        }
        else {
            return em.merge(author);
        }
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Author a where a.idAuthor = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateNameById(long id, String  name) {
        Query query = em.createQuery("update Author a set a.nameAuthor = :name WHERE a.idAuthor = :id");
        query.setParameter("name", name);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Optional<Author> getById(long id) {
//        Author author = null;
//        try {
//            author =  namedParameterJdbcOperations.queryForObject("select * from author where id_author =:id",
//                    Map.of("id", id), new AuthorMapper());
//        }catch (EmptyResultDataAccessException e){
//            return null;
//        }
//        return author;

        return Optional.ofNullable(em.find(Author.class,id));
    }

    @Override
    public List<Author> getAll() {
//        return namedParameterJdbcOperations.query("select * from author", new AuthorMapper());
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public Author getByNameOrCreate(Author author) {
        TypedQuery<Author> query = em.createQuery("Select a from Author a WHERE a.nameAuthor = :name", Author.class);
        query.setParameter("name", author.getNameAuthor());
        List<Author> authors = query.getResultList();

        if (!authors.isEmpty()) {
            return authors.get(0);
        } else {
            return em.merge(author);
        }
    }
}
