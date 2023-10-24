package com.example.elcstore.exception;

import java.util.UUID;

public class ProductOptionInUseException extends InUseException {

    private static final String PRODUCT_OPTION_CANNOT_BE_DELETED = "ProductOption with ID = %s cannot be deleted because it is used";

    public ProductOptionInUseException(UUID id) {
        super(String.format(PRODUCT_OPTION_CANNOT_BE_DELETED, id));
    }
}
