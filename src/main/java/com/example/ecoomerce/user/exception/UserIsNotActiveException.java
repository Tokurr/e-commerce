package com.example.ecoomerce.user.exception;


public class UserIsNotActiveException extends RuntimeException{
    public UserIsNotActiveException(String message) {
        super(message);
    }
}
