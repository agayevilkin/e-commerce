package com.example.elcstore.exception;

public class ImageUploadException extends RuntimeException {

    public static final String FAILED_UPLOAD_IMAGE = "Image uploading is a failure!";
    public ImageUploadException(String message) {
        super(message);
    }
}
