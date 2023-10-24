package com.example.elcstore.exception;

public class InvalidTokenException extends InvalidStateException {

    public static final String INVALID_TOKEN = "INVALID TOKEN: { %s }";

    public InvalidTokenException(String message) {
        super(String.format(INVALID_TOKEN, message));
    }
}
