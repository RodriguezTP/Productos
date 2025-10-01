package com.practica.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor

public class ErrorResponse {
    private String message;
    private int statusCode;
    private LocalDateTime timestamp;
    private String errorDetails;

    public ErrorResponse(String errorDetails, String message, int statusCode) {
        this.errorDetails = errorDetails;
        this.message = message;
        this.statusCode = statusCode;
    }

    public ErrorResponse(String errorDetails, String message, int statusCode, LocalDateTime timestamp) {
        this.errorDetails = errorDetails;
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
    }


    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
