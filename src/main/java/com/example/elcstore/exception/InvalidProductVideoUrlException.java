package com.example.elcstore.exception;

public class InvalidProductVideoUrlException extends InvalidStateException {

    public static final String INVALID_URL = "The provided video URL is not valid.";

    public InvalidProductVideoUrlException(String message) {
        super(message);
    }
}
