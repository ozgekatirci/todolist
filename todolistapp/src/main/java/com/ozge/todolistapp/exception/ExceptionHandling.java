package com.ozge.todolistapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String>handleNullPointerException(NullPointerException e){
        return new ResponseEntity<>("Null pointer exception"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String>handleEntityNotFoundException(EntityNotFoundException e){
        return new ResponseEntity<>("Entity not found exception"+e.getMessage(), HttpStatus.NOT_FOUND);
    }


}
