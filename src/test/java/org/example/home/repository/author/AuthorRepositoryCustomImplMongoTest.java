package org.example.home.repository.author;



import lombok.val;
import org.example.home.domain.Author;
import org.example.home.repository.AbstractRepositoryMongoTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class AuthorRepositoryCustomImplMongoTest extends AbstractRepositoryMongoTest {


//    @Autowired
//    private TestEntityManager em;
    @Autowired
    private MongoTemplate mongoTemplate;



    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName("должен добавлять в бд автора или возвращать по его ")
    @Test
    void getByNameOrCreate() {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("id").is(FIRST_AUTHOR_ID));
        val author = mongoTemplate.save(new Author("Pushkin", "1812-01-01"));
        val authors = authorRepository.findAll();
        val firstAuthor = authors.get(0);
        val actualAuthor  = authorRepository.getByNameOrCreate(author).get();
 //               authorRepository.getByNameOrCreate(firstAuthor);
        assertThat(actualAuthor).isEqualTo(firstAuthor);


        val newAuthor = new Author("aaaa", "1993-05-11");
        val actualAuthorElse = authorRepository.getByNameOrCreate(newAuthor);
        assertThat(actualAuthorElse).get().isEqualTo(newAuthor);


//        val firstAuthor = em.find(Author.class, FIRST_AUTHOR_ID);
//        val actualAuthor =  authorRepository.getByNameOrCreate(firstAuthor);
//
//        assertThat(actualAuthor).isEqualTo(firstAuthor);
//
//
//        val newAuthor = new Author("2","aaaaa", "1993-05-11");
//        val actualAuthorElse = authorRepository.getByNameOrCreate(newAuthor);
//        assertThat(actualAuthorElse).isEqualTo(newAuthor);
//     assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);

    }
}