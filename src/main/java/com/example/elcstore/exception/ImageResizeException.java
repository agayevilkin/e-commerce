package com.example.elcstore.exception;

public class ImageResizeException extends ImageProcessingException {

    public static final String FAILED_RESIZE_IMAGE = "Image resizing has failed : { %s }";

    public ImageResizeException(String message) {
        super(String.format(FAILED_RESIZE_IMAGE, message));
    }
}
