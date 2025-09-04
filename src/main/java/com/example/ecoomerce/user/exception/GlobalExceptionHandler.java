package com.example.ecoomerce.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(Exception exception)
    {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserIsNotActiveException.class)
    public ResponseEntity<Object> UserIsNotActiveException(Exception exception)
    {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommunicationInfoNotFoundException.class)
    public ResponseEntity<Object> communicationInfoNotFound(Exception exception)
    {
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

}
