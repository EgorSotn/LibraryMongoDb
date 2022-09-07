package org.example.home.repository.book;

import org.example.home.domain.Book;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {
    @Override
    List<Book> findAll();

    //    @EntityGraph(attributePaths = {"author","genres"})
//    List<Book> findAll();
//
//    @Modifying
//    @Query("update Book b set b.name =:name WHERE b.idBook =:id")
//    void updateNameById(@Param("name")String name,@Param("id") long id);
}