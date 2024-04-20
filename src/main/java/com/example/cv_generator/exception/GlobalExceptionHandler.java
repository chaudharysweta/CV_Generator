package com.example.cv_generator.exception;

import com.example.cv_generator.dto.ApiResponse;
import com.example.cv_generator.dto.CustomMessageSource;
import com.example.cv_generator.dto.GlobalApiResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private CustomMessageSource customMessageSource;


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> handleApiException(ApiException ex) {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, true);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> resp = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            resp.put(fieldName, message);
        });
        return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<GlobalApiResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ex.printStackTrace();
        if (ex.getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException violation =
                    ((ConstraintViolationException) ex.getCause());
            String constraintName = violation.getConstraintName();
            List<String> stringList =
                    Arrays.stream(constraintName.split("_")).map(x ->
                            x.toLowerCase()).collect(Collectors.toList());
            String key = stringList.get(stringList.size() - 1);
            String violatedConstraintMessage;
            if (constraintName.contains("uk_")) {
                violatedConstraintMessage =
                        customMessageSource.get("violated.unique.constraint",
                                customMessageSource.get(key));
                GlobalApiResponse globalAPIResponse = new
                        GlobalApiResponse(false, violatedConstraintMessage, null);
                return ResponseEntity.badRequest().body(globalAPIResponse);
            } else if (constraintName.contains("fk_")) {
                violatedConstraintMessage =
                        customMessageSource.get("violated.foreign.constraint",
                                customMessageSource.get(key));
                GlobalApiResponse globalAPIResponse = new
                        GlobalApiResponse(false, violatedConstraintMessage, null);
                return ResponseEntity.badRequest().body(globalAPIResponse);
            }
        }
        if (ex.getCause() instanceof DataException) {
            return new ResponseEntity<>(new GlobalApiResponse(false,
                    customMessageSource.get("invalid.data.validation"), "Issue occurred with Relational data"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new GlobalApiResponse(false,
                customMessageSource.get("please.contact.operator"),
                ex.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

