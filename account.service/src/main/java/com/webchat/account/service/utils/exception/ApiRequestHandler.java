package com.webchat.account.service.utils.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiRequestHandler {
    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<Object> handleApiRequestExceptions(ApiRequestException ex) {

        ApiException apiException = new ApiException(
                ex.getMessage(),
                ex.getStatus(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, ex.getStatus());
    }
}
