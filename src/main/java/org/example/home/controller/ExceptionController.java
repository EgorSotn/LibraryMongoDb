package org.example.home.controller;

import org.example.home.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.xml.bind.ValidationException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleException(ValidationException e){
        return ResponseEntity.badRequest().body("{\n" +
                "\"message\" : \"" +
                e.getMessage() + "\"\n}");
    }
}
