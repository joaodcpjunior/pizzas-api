package com.joaodcpjunior.pizzas.exceptions;

import com.joaodcpjunior.pizzas.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        StandardError err = new StandardError(
                System.currentTimeMillis(),
                httpStatus.value(),
                "Object not found",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(httpStatus).body(err);
    }
}