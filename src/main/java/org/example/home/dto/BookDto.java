package org.example.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String idBook;
    private String nameBook;
    private String yearBook;
    private String nameAuthor;
    private String yearAuthor;
    private String namesGenre;


}
