package com.example.elcstore.exception;

import java.util.UUID;

public class ColorInUseException extends InUseException {

    public static final String COLOR_CANNOT_BE_DELETED = "Color with ID = %s cannot be deleted because it is used";

    public ColorInUseException(UUID id) {
        super(String.format(COLOR_CANNOT_BE_DELETED, id));
    }
}
