package com.webchat.account.service.utils.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiRequestException extends RuntimeException {
    private HttpStatus status = HttpStatus.BAD_REQUEST;
    public ApiRequestException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public ApiRequestException(String message, Throwable cause, HttpStatus status){
        super(message, cause);
        this.status = status;
    }

    public ApiRequestException(String message){
        super(message);
    }

    public ApiRequestException(String message, Throwable cause){
        super(message, cause);
    }

}
