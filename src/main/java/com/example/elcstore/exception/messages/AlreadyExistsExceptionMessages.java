package com.example.elcstore.exception.messages;

import lombok.Getter;

@Getter
public enum AlreadyExistsExceptionMessages {
    USERNAME_ALREADY_EXISTS("Username already exists!"),
    EMAIL_ALREADY_EXISTS("This email address is already in use. Please try another.");

    private final String message;

    AlreadyExistsExceptionMessages(String message) {
        this.message = message;
    }

}
