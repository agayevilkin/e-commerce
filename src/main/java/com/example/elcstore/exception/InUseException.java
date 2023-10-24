package com.example.elcstore.exception;

public class InUseException extends RuntimeException {
    public InUseException(String message) {
        super(message);
    }
}
