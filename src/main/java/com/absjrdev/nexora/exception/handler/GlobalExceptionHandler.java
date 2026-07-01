package com.absjrdev.nexora.exception.handler;

import com.absjrdev.nexora.exception.ResourceNotFoundException;
import com.absjrdev.nexora.exception.model.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

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
}
