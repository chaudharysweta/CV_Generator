package com.example.cv_generator.exception;

import com.example.cv_generator.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse apiResponse=new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> handleApiException(ApiException ex){
        String message = ex.getMessage();
        ApiResponse apiResponse=new ApiResponse(message,true);
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }
}
