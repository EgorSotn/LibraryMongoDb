package org.example.home.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;


@Document("genres")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Genre {


    @Id
    private String idGenre;

    @Indexed(unique = true)
    @Field("name")
    private String nameGenre;

    public Genre(String nameGenre) {
        this.nameGenre = nameGenre;
    }
}
