package com.example.elcstore.exception;

public class ImageDimensionsRetrievalException extends ImageProcessingException {
    public static final String FAILED_GET_IMAGE_DIMENSIONS = "Failed to retrieve image dimensions: { %s }";

    public ImageDimensionsRetrievalException(String message) {
        super(String.format(FAILED_GET_IMAGE_DIMENSIONS, message));
    }
}
