package com.example.elcstore.exception;

public class ImageProcessingException extends RuntimeException{

    public static final String FAILED_RESIZE_IMAGE = "Image resizing has failed!";
    public static final String FAILED_GET_IMAGE_DIMENSIONS = "Failed to retrieve image width and height! (check the file format)";

    public ImageProcessingException(String message) {
        super(message);
    }
}
