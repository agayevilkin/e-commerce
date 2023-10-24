package com.example.elcstore.exception;

public class UnsupportedImageTypeException extends RuntimeException {

    public static final String UNSUPPORTED_IMAGE_TYPE = "Unsupported image type! (check the file format)";

    public UnsupportedImageTypeException() {
        super(UNSUPPORTED_IMAGE_TYPE);
    }
}
