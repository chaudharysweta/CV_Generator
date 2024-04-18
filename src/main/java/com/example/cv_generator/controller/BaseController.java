package com.example.cv_generator.controller;

import com.example.cv_generator.dto.CustomMessageSource;
import com.example.cv_generator.dto.GlobalApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseController {

    @Autowired
    protected CustomMessageSource customMessageSource;

    @Autowired
    protected ObjectMapper objectMapper;
    protected String messageCode;

    protected GlobalApiResponse successResponse(String message, Object data){
        GlobalApiResponse globalApiResponse=new GlobalApiResponse();
        globalApiResponse.setStatus(true);
        globalApiResponse.setMessage(message);
        globalApiResponse.setData(data);
        return globalApiResponse;
    }
    protected GlobalApiResponse errorResponse(String message, Object errors) {
        GlobalApiResponse globalApiResponse = new GlobalApiResponse();
        globalApiResponse.setStatus(false);
        globalApiResponse.setMessage(message);
        globalApiResponse.setData(errors);
        return globalApiResponse;
    }


}
