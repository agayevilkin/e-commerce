package com.example.elcstore.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends DefaultErrorAttributes {

    public static final String STATUS = "status";
    public static final String MESSAGE = "message";
    public static final String PATH = "path";
    public static final String ERROR = "error";
    public static final String ERRORS = "errors";
    public static final String TIMESTAMP = "timestamp";

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Map<String, Object>> handle(NotFoundException ex, WebRequest request) {
        log.trace("Resource not found {}", ex.getMessage());
        return ofType(request, HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public final ResponseEntity<Map<String, Object>> handle(MaxUploadSizeExceededException ex, WebRequest request) {
        log.trace("Maximum upload size exceeded: {}", ex.getMessage());
        return ofType(request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InvalidStateException.class)
    public final ResponseEntity<Map<String, Object>> handle(InvalidStateException ex, WebRequest request) {
        log.trace("Request is invalid state: {}", ex.getMessage());
        return ofType(request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ImageProcessingException.class)
    public final ResponseEntity<Map<String, Object>> handle(ImageProcessingException ex, WebRequest request) {
        log.trace("Image processing is failed: {}", ex.getMessage());
        return ofType(request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(UnsupportedImageTypeException.class)
    public final ResponseEntity<Map<String, Object>> handle(UnsupportedImageTypeException ex, WebRequest request) {
        log.trace("Unsupported image type: {}", ex.getMessage());
        return ofType(request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InUseException.class)
    public final ResponseEntity<Map<String, Object>> handle(InUseException ex, WebRequest request) {
        log.trace("Data in use: {}", ex.getMessage());
        return ofType(request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // TODO: 9/25/2023 Change this message or change use place
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public final ResponseEntity<Map<String, Object>> handle(DataIntegrityViolationException ex, WebRequest request) {
//        log.trace("This data is used by other entity {}", ex.getMessage());
//        return ofType(request, HttpStatus.BAD_REQUEST, "This data cannot be deleted because it is used");
//    }


    @ExceptionHandler(AlreadyExistsException.class)
    public final ResponseEntity<Map<String, Object>> handle(AlreadyExistsException ex, WebRequest request) {
        log.trace("Resource is already exists: {}", ex.getMessage());
        return ofType(request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    protected ResponseEntity<Map<String, Object>> ofType(WebRequest request, HttpStatus status, String message) {
        return ofType(request, status, message, Collections.EMPTY_LIST);
    }

    private ResponseEntity<Map<String, Object>> ofType(WebRequest request, HttpStatus status, String message, List validationErrors) {
        Map<String, Object> attributes = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        attributes.put(STATUS, status.value());
        attributes.put(ERROR, status.getReasonPhrase());
        attributes.put(MESSAGE, message);
        attributes.put(ERRORS, validationErrors);
        attributes.put(PATH, ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(attributes, status);
    }

    private String getConstraintViolationExceptionMessage(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()).get(0);
    }
}

