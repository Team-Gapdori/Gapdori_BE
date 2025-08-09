package com.example.gapdori.global.exception;

import com.example.gapdori.global.exception.model.CustomException;
import com.example.gapdori.global.exception.model.ErrorCode;
import com.example.gapdori.global.exception.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class GlobalExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try{
            filterChain.doFilter(request, response);
        } catch (CustomException e){
            ErrorCode errorCode = e.getErrorCode();
            ErrorResponse errorResponse = ErrorResponse.of(errorCode, e);
            errorToJson(errorCode, errorResponse, response);
        } catch (Exception e){
            ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
            ErrorResponse errorResponse = ErrorResponse.of(errorCode, e);
            errorToJson(errorCode, errorResponse, response);
        }
    }

    private void errorToJson(ErrorCode errorCode, ErrorResponse errorResponse, HttpServletResponse response) throws IOException {
        response.setStatus(errorCode.getStatusCode());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}
