package com.example.elcstore.exception;

import java.util.UUID;

public class CategoryInUseException extends InUseException {

    public static final String CATEGORY_CANNOT_BE_DELETED = "Category with ID = %s cannot be deleted because it is used";

    public CategoryInUseException(UUID id) {
        super(String.format(CATEGORY_CANNOT_BE_DELETED, id));
    }
}
