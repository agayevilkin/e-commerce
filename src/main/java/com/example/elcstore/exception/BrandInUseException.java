package com.example.elcstore.exception;

import java.util.UUID;

public class BrandInUseException extends InUseException {

    public final static String BRAND_CANNOT_BE_DELETED = "Brand with ID = %s cannot be deleted because it is used";

    public BrandInUseException(UUID id) {
        super(String.format(BRAND_CANNOT_BE_DELETED, id));
    }
}
