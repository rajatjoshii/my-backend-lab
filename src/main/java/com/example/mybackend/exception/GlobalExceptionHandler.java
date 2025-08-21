package com.example.mybackend.exception;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;

import com.example.mybackend.exception.ApiErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //this will catch errors where we are trying to update those values that dont exist via put
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchElementException.class)
    public ApiErrorResponse handleNoSuchApiErrorResponse(NoSuchElementException ex) {
        Map<String, String> errorDetails = Map.of("message", ex.getMessage()+ "better luck next time");

        return new ApiErrorResponse(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            errorDetails
        );
    }

    //handle invalid json
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ApiErrorResponse handleInvalidJson(HttpMessageNotReadableException ex) {
        return new ApiErrorResponse(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Malformed JSON request",
            Map.of("message", ex.getMostSpecificCause().getMessage())
        );
    }

}
