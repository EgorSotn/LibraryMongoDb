package org.example.home.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.example.home.domain.Author;
import org.example.home.domain.Book;
import org.example.home.domain.Genre;
import org.example.home.dto.BookDto;
import org.example.home.service.BookService;
import org.example.home.service.ConvertDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
class BookControllerRestTest {
    private static final String ID_BOOK1 = "1";
    private static final String BOOK_NAME1 = "Eva";
    private static final String BOOK_YEAR1 = "2000-01-01";
    private static final String GENRE_NAME1 = "Drama";
    private static final String NAME_AUTHOR1 = "Pushkin";
    private static final String YEAR_AUTHOR1 = "1899-05-22";

    @MockBean
    BookService bookService;

    @MockBean
    ConvertDto convertDto;
    MockMvc mvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @BeforeEach
    public void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
    @Test
    void getAllPerson() throws Exception {
        given(bookService.getAllBook()).willReturn(List.of(new Book(ID_BOOK1,BOOK_NAME1, BOOK_YEAR1,
                new Genre(GENRE_NAME1), new Author(NAME_AUTHOR1, YEAR_AUTHOR1))));

        given(convertDto.bookConvertToDto(any())).willReturn(new BookDto(ID_BOOK1, BOOK_NAME1, BOOK_YEAR1, NAME_AUTHOR1,
                YEAR_AUTHOR1, GENRE_NAME1));


        List<BookDto> bookDtoList = new ArrayList<>();
        bookDtoList.add(convertDto.bookConvertToDto(any()));


        mvc.perform(get("/api/book")).andDo(print())
                .andExpect(status().isOk());

        String content = mvc.perform(get("/api/book")).andReturn().getResponse().getContentAsString();


        BookDto[] bookDtos = this.mapFromJson(content, BookDto[].class);
        assertTrue(bookDtos.length > 0);

    }

    @Test
    void getBookById() throws Exception {
        val bookDto = new BookDto(ID_BOOK1, BOOK_NAME1, BOOK_YEAR1, NAME_AUTHOR1,
                YEAR_AUTHOR1, GENRE_NAME1);
        val book = new Book(ID_BOOK1,BOOK_NAME1, BOOK_YEAR1,
                new Genre(GENRE_NAME1), new Author(NAME_AUTHOR1, YEAR_AUTHOR1));

        given(bookService.getById(any())).willReturn(book);
        given(convertDto.bookConvertToDto(any())).willReturn(bookDto);

        mvc.perform(get("/api/book/{id}", bookDto.getIdBook())).andDo(print())
                .andExpect(status().isOk());

        String content = mvc.perform(get("/api/book/{id}", bookDto.getIdBook()))
                .andReturn().getResponse().getContentAsString();


        BookDto bookDtoInRequest = this.mapFromJson(content, BookDto.class);
        assertThat(bookDtoInRequest).isEqualTo(bookDto);
    }

    @Test
    void createBook() throws Exception {
        val bookDto = new BookDto(ID_BOOK1, BOOK_NAME1, BOOK_YEAR1, NAME_AUTHOR1,
                YEAR_AUTHOR1, GENRE_NAME1);

        val book = new Book(ID_BOOK1,BOOK_NAME1, BOOK_YEAR1,
                new Genre(GENRE_NAME1), new Author(NAME_AUTHOR1, YEAR_AUTHOR1));

        given(bookService.createBook(any()))
                .willReturn(book);
        given(convertDto.bookConvertToDto(any())).willReturn(bookDto);



        MvcResult mvcResult = mvc.perform(post("/api/book")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(bookDto))).andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(201);
        String content = mvcResult.getResponse().getContentAsString();
        assertThat(content).isEqualTo(mapToJson(bookDto));
    }

    @Test
    void deleteBook() throws Exception {

        MvcResult mvcResult = mvc.perform(delete("/api/book/delete/{id}", ID_BOOK1)).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void updateBook() throws Exception {
        val bookDto = new BookDto(ID_BOOK1, BOOK_NAME1, BOOK_YEAR1, NAME_AUTHOR1,
                YEAR_AUTHOR1, GENRE_NAME1);
        val book = new Book(ID_BOOK1,BOOK_NAME1, BOOK_YEAR1,
                new Genre(GENRE_NAME1), new Author(NAME_AUTHOR1, YEAR_AUTHOR1));
        given(convertDto.fromBookDtoToBook(any())).willReturn(book);
        given(bookService.updateBook(any())).willReturn(book);

        given(convertDto.bookConvertToDto(any())).willReturn(bookDto);
        MvcResult mvcResult = mvc.perform(put("/api/book/update")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(bookDto))).andReturn();
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);

        String content = mvcResult.getResponse().getContentAsString();

        assertThat(content).isEqualTo(mapToJson(convertDto.bookConvertToDto(any())));
    }
}