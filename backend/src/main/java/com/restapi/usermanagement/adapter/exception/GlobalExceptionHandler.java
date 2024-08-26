package com.restapi.usermanagement.adapter.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.restapi.usermanagement.adapter.exception.customs.DataConflictException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final String validationError = "Validation Error";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> errorDetails = buildErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error",
                ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
        });

        Map<String, Object> errorDetails = buildErrorDetails(HttpStatus.BAD_REQUEST, validationError, fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Map<String, Object>> handleBindException(BindException e) {
        String message = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        Map<String, Object> errorDetails = buildErrorDetails(HttpStatus.BAD_REQUEST, validationError, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
            WebRequest request) {
        String requiredType = ex.getRequiredType().getSimpleName();
        String error = ex.getName() + " should be of type '" + requiredType + "'.";
        Map<String, Object> errorDetails = buildErrorDetails(HttpStatus.BAD_REQUEST, validationError, error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, Object>> handleHandlerMethodValidationException(
            HandlerMethodValidationException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
        });

        Map<String, Object> errorDetails = buildErrorDetails(HttpStatus.BAD_REQUEST, validationError, fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> handleNoSuchElementException(NoSuchElementException e) {
        Map<String, Object> errorDetails = buildErrorDetails(HttpStatus.NOT_FOUND, "Data Not Found.", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoResourceFoundException(NoResourceFoundException ex,
            WebRequest request) {
        Map<String, Object> errorDetails = buildErrorDetails(HttpStatus.NOT_FOUND, "Resource Not Found",
                ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoHandlerFoundException(NoHandlerFoundException ex,
            WebRequest request) {
        Map<String, Object> errorDetails = buildErrorDetails(HttpStatus.NOT_FOUND, "Handler Not Found",
                ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(DataConflictException.class)
    public ResponseEntity<Map<String, Object>> handleDataConflictException(DataConflictException ex) {
        Map<String, Object> errorDetails = buildErrorDetails(HttpStatus.CONFLICT, "Conflict", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }

    private Map<String, Object> buildErrorDetails(HttpStatus status, String error, Object message) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now().toString());
        errorDetails.put("status", status.value());
        errorDetails.put("error", error);
        errorDetails.put("message", message);
        return errorDetails;
    }
}
