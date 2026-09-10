package com.absjrdev.abscommerce.exception.handler;

import com.absjrdev.abscommerce.exception.BusinessException;
import com.absjrdev.abscommerce.exception.DatabaseException;
import com.absjrdev.abscommerce.exception.ResourceNotFoundException;
import com.absjrdev.abscommerce.exception.model.StandardError;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice
public
class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public
    ResponseEntity<StandardError> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        String error = "Resource Not Found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public
    ResponseEntity<StandardError> handleDatabaseException(DatabaseException ex, HttpServletRequest request) {
        String error = "Database Error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(BusinessException.class)
    public
    ResponseEntity<StandardError> handleBusinessException(BusinessException ex, HttpServletRequest request) {
        String error = "Business Error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpServletRequest request
    ) {
        String message = "Invalid request body.";

        if (ex.getCause() instanceof InvalidFormatException invalidFormatException) {

            if (invalidFormatException.getTargetType() != null
                    && invalidFormatException.getTargetType().isEnum()) {

                String acceptedValues = Arrays.stream(
                                invalidFormatException.getTargetType().getEnumConstants()
                        )
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));

                message = "Invalid value '"
                        + invalidFormatException.getValue()
                        + "'. Accepted values: "
                        + acceptedValues
                        + ".";
            }
        }

        String error = "Invalid Request";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                message,
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }
}
