package org.example.home.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import javax.persistence.*;

@Document("authors")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Author {

    @Id
    private String idAuthor;

    @Field("name")
    @Indexed(unique = true)
    private String nameAuthor;

    @Field("year")
    private String year;

    public Author(String nameAuthor, String year) {
        this.nameAuthor = nameAuthor;
        this.year = year;
    }
}
