package com.example.cv_generator.controller;

import com.example.cv_generator.config.MessageCodeConstant;
import com.example.cv_generator.config.MessageConstant;
import com.example.cv_generator.dto.ApiResponse;
import com.example.cv_generator.dto.CustomMessageSource;
import com.example.cv_generator.dto.ExperienceInformationDto;
import com.example.cv_generator.dto.GlobalApiResponse;
import com.example.cv_generator.service.ExperienceInformationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exp-info")
public class ExperienceInformationController extends BaseController{

    private final ExperienceInformationService experienceInformationService;

    private final CustomMessageSource customMessageSource;



    public ExperienceInformationController(ExperienceInformationService experienceInformationService, CustomMessageSource customMessageSource) {
        this.experienceInformationService = experienceInformationService;
        this.customMessageSource = customMessageSource;
        this.messageCode= MessageCodeConstant.EXPERIENCE_INFORMATION;
    }

    @PostMapping("/create/{basic-info-id}")
    public ResponseEntity<GlobalApiResponse> createExpInfo(@Valid @RequestBody ExperienceInformationDto experienceInformationDto,
                                                           @PathVariable("basic-info-id") Short basicInfoId){
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_CREATE,customMessageSource
                        .get(messageCode)),experienceInformationService.createExpInfo(experienceInformationDto,basicInfoId)), HttpStatus.OK);
    }
    @PutMapping("/update/{exp-info-id}")
    public ResponseEntity<GlobalApiResponse> updateExpInfo(@Valid @RequestBody ExperienceInformationDto experienceInformationDto,
                                                                  @PathVariable("exp-info-id") Short expInfoId) {
        experienceInformationService.updateExpInfo(experienceInformationDto,expInfoId);
        return ResponseEntity.ok(successResponse(customMessageSource.get(MessageConstant.CRUD_UPDATE,customMessageSource
                .get(MessageCodeConstant.EXPERIENCE_INFORMATION)),null));
    }



    @DeleteMapping("/delete/{exp-info-id}")
    public ResponseEntity<GlobalApiResponse> deteteExpInfo(@PathVariable("exp-info-id") Short expInfoId){
        if (expInfoId==null){
            throw new NullPointerException(MessageConstant.ID_NULL);
        }
        experienceInformationService.deleteExpInfo(expInfoId);
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_DELETE,customMessageSource
                        .get(MessageCodeConstant.EXPERIENCE_INFORMATION)),"Id:"+expInfoId),HttpStatus.OK);
    }



    //get all author
    @GetMapping("/get-all-exp")
    public ResponseEntity<GlobalApiResponse> getAllExperience(){
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_GET_ALL,customMessageSource
                .get(messageCode)),experienceInformationService.getAllExpInfo()),HttpStatus.OK);
    }

    //get by id
    @GetMapping("/{exp-info-id}")
    public ResponseEntity<GlobalApiResponse>getSingleExperience(@PathVariable("exp-info-id") Short expInfoId){
        if (expInfoId==null){
            throw new NullPointerException(MessageConstant.ID_NULL);
        }
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_GET,customMessageSource
                .get(MessageCodeConstant.EXPERIENCE_INFORMATION)),experienceInformationService.getExpInfoById(expInfoId)),HttpStatus.OK);
    }

}
