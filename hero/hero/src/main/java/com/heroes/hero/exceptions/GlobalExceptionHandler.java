package com.heroes.hero.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HeroNotFoundException.class)
    public ResponseEntity<ErrorObject> handleHeroNotFoundException(HeroNotFoundException ex, WebRequest request){

        ErrorObject error = new ErrorObject();

        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessage((ex.getMessage()));
        error.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorObject> handleReviewNotFoundException(HeroNotFoundException ex, WebRequest request){

        ErrorObject error = new ErrorObject();

        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessage((ex.getMessage()));
        error.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(error, HttpStatus.NOT_FOUND);
    }
}
