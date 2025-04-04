package com.salman.poi.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.stream.Collectors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ApiError(
                HttpStatus.NOT_FOUND,
                "POI not found",
                ex.getMessage()
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        String combinedErrors = String.join("; ", errorMessages);

        return new ResponseEntity<>(new ApiError(
                HttpStatus.BAD_REQUEST,
                "Validation failed",
                combinedErrors
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNotFound(NoSuchElementException ex) {
        return new ResponseEntity<>(new ApiError(
                HttpStatus.NOT_FOUND,
                "Resource not found",
                ex.getMessage()
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneric(Exception ex) {
        return new ResponseEntity<>(new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Something went wrong",
                ex.getMessage()
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public static class ApiError {
        public LocalDateTime timestamp = LocalDateTime.now();
        public HttpStatus status;
        public String error;
        public String message;

        public ApiError(HttpStatus status, String error, String message) {
            this.status = status;
            this.error = error;
            this.message = message;
        }
    }
}