package com.example.cv_generator.exception;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
