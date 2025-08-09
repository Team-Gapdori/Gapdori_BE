package com.example.gapdori.global.exception;

import com.example.gapdori.global.exception.model.CustomException;
import com.example.gapdori.global.exception.model.ErrorCode;
import com.example.gapdori.global.exception.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customExceptionHandling(CustomException e){
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(errorCode, e);
        return ResponseEntity.status(errorResponse.statusCode())
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandling(Exception e){
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode, e);
        return ResponseEntity.status(errorResponse.statusCode())
                .body(errorResponse);
    }
}

