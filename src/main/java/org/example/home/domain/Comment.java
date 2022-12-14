package org.example.home.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;


@Document("comments")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @Field("id")
    private String id_comment;

    @DBRef
    @Field("book")
    private Book book;

    @Field("text")
    private String text;

    public Comment(String id_comment, String text) {
        this.id_comment = id_comment;
        this.text = text;
    }

    public Comment(String text) {
        this.text = text;
    }



    @Override
    public String toString() {
        return "Comment{" +
                "idComment=" + id_comment +
                ", text='" + text + '\'' +
                '}';
    }

}
