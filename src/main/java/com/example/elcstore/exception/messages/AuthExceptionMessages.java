package com.example.elcstore.exception.messages;

import lombok.Getter;

@Getter
public enum AuthExceptionMessages {

    //todo change message to other class
    INVALID_TOKEN("Invalid Token!");

    private final String message;

    AuthExceptionMessages(String message) {
        this.message = message;
    }
}
