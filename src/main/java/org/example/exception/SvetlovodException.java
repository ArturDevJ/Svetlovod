package org.example.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SvetlovodException extends RuntimeException {
    private final String errorCode;
    private final String errorDescription;
    private final HttpStatus statusCode;

    public SvetlovodException(String errorCode, String errorDescription, HttpStatus statusCode) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.statusCode = statusCode;
    }
}
