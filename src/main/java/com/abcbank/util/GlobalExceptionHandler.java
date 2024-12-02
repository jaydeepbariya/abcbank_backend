package com.abcbank.util;

import com.abcbank.exception.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleCustomException(Exception ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), ex.getLocalizedMessage(), 400);
        logger.error("Exception: " + ex.getMessage(), ex);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}

