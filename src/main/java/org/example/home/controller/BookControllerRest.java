package org.example.home.controller;


import lombok.RequiredArgsConstructor;
import org.example.home.domain.Book;
import org.example.home.dto.BookDto;
import org.example.home.service.BookService;
import org.example.home.service.ConvertDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
@RequiredArgsConstructor
public class BookControllerRest {
    private final BookService bookService;
    private final ConvertDto convertDto;

    @GetMapping("/api/book")
    public ResponseEntity<List<BookDto>> getAllPerson(){
        List<BookDto> bookDtos = new ArrayList<>();
        bookService.getAllBook().stream().forEach(book -> bookDtos.add(convertDto.bookConvertToDto(book)));

        return new ResponseEntity<>(bookDtos , HttpStatus.OK);
    }
    @GetMapping("/api/book/{id}")
    public ResponseEntity<BookDto> getBookById(
            @PathVariable("id") String id
    ){
        return new ResponseEntity<>(convertDto.bookConvertToDto(bookService.getById(id)), HttpStatus.OK);
    }
    @PostMapping("/api/book")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto){
        Book book = convertDto.fromBookDtoToBook(bookDto);

        return new ResponseEntity<>(convertDto.bookConvertToDto(bookService.createBook(book)), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/book/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") String id){
         bookService.deleteBook(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/api/book/update")
    public ResponseEntity<BookDto> updateBook(
            @RequestBody BookDto updateBookDto){
       Book bookInUpd= convertDto.fromBookDtoToBook(updateBookDto);
       Book updateBook = bookService.updateBook(bookInUpd);
       BookDto bookDto = convertDto.bookConvertToDto(updateBook);

        return new ResponseEntity<>(bookDto,HttpStatus.OK);
    }
}
