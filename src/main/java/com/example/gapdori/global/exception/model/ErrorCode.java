package com.example.gapdori.global.exception.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "서버오류입니다.");

    private final int statusCode;
    private final String message;
}
