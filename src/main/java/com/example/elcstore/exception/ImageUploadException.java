package com.example.elcstore.exception;

public class ImageUploadException extends ImageProcessingException {

    public static final String FAILED_UPLOAD_IMAGE = "Image uploading has failed: { %s }";

    public ImageUploadException(String message) {
        super(String.format(FAILED_UPLOAD_IMAGE, message));
    }
}
