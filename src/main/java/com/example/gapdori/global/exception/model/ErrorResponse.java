package com.example.gapdori.global.exception.model;


import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        int statusCode,
        String message,
        LocalDateTime timestamp,
        String exception
) {
    public static ErrorResponse of(ErrorCode errorCode, Exception e){
        return ErrorResponse.builder()
                .statusCode(errorCode.getStatusCode())
                .message(errorCode.getMessage())
                .timestamp(LocalDateTime.now())
                .exception(e.getClass().getSimpleName())
                .build();
    }
}

