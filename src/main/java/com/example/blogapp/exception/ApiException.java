package com.example.blogapp.exception;

public class ApiException extends  RuntimeException {

    public ApiException(String message) {
        super(message);
    }
    public ApiException() {
        super();
    }
}
