package com.example.elcstore.exception;

public class UsernameAlreadyExistsException extends AlreadyExistsException{

    private static final String USERNAME_ALREADY_EXISTS = "This username is already in use. Please try another.";

    public UsernameAlreadyExistsException() {
        super(USERNAME_ALREADY_EXISTS);
    }
}
