package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.dto.response.ErrorResponse;
import org.example.exception.SvetlovodException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SvetExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(SvetlovodException e, HttpServletRequest r) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorCode(e.getErrorCode())
                .errorDescription(e.getErrorDescription())
                .build(), e.getStatusCode());
    }
}
