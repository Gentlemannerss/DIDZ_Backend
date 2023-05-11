package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.IndexOutOfBoundsException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = LoginException.class)
    public ResponseEntity<Object> handleLoginException(LoginException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = ReviewSubmissionException.class)
    public ResponseEntity<Object> handleReviewSubmissionException(ReviewSubmissionException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ProductAccessException.class)
    public ResponseEntity<Object> handleProductAccessException(ProductAccessException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = StudyGroupEnrollmentException.class)
    public ResponseEntity<Object> handleStudyGroupEnrollmentException(StudyGroupEnrollmentException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PriceIndicationException.class)
    public ResponseEntity<Object> handlePriceIndicationException(PriceIndicationException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception (RecordNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IndexOutOfBoundsException.class)
    public ResponseEntity<Object> exception (IndexOutOfBoundsException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ToManyCharException.class)
    public ResponseEntity<Object> exception (ToManyCharException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.LENGTH_REQUIRED);
    }
}