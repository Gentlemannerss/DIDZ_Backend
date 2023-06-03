package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.exceptions.*;
import com.digicoachindezorg.didz_backend.exceptions.IndexOutOfBoundsException; /*Test of het Java.Lang is of mijn eigen backend.Exception*/
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {
            RecordNotFoundException.class,
            AlreadyExistsException.class,
            IndexOutOfBoundsException.class, //Welke IndexOutOfBounds moet ik hebben, die van java of die van mezelf?
            InvalidPasswordException.class,
            ToManyCharException.class
    })
    public ResponseEntity<Object> handleException(RuntimeException exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // Default status for unhandled exceptions

        if (exception instanceof RecordNotFoundException || exception instanceof IndexOutOfBoundsException) {
            status = HttpStatus.NOT_FOUND;
        } else if (exception instanceof AlreadyExistsException) {
            status = HttpStatus.CONFLICT;
        } else if (exception instanceof InvalidPasswordException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (exception instanceof ToManyCharException) {
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(exception.getMessage(), status);
    }
}