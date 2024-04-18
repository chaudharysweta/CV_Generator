package com.example.cv_generator.controller;

import com.example.cv_generator.config.MessageCodeConstant;
import com.example.cv_generator.config.MessageConstant;
import com.example.cv_generator.dto.ApiResponse;
import com.example.cv_generator.dto.CustomMessageSource;
import com.example.cv_generator.dto.EducationInformationDto;
import com.example.cv_generator.dto.GlobalApiResponse;
import com.example.cv_generator.service.EducationInformationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edu-info")
public class EducationInformationController extends BaseController{

    private final EducationInformationService educationInformationService;

    private final CustomMessageSource customMessageSource;

    public EducationInformationController(EducationInformationService educationInformationService, CustomMessageSource customMessageSource) {
        this.educationInformationService = educationInformationService;
        this.customMessageSource = customMessageSource;
        this.messageCode= MessageCodeConstant.EDUCATION_INFORMATION;
    }

    @PostMapping("/create/{basic-info-id}")
    public ResponseEntity<GlobalApiResponse> createEduInfo(@Valid @RequestBody EducationInformationDto educationInformationDto,
                                                           @PathVariable("basic-info-id") Short basicInfoId){
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_CREATE,customMessageSource
                .get(messageCode)),educationInformationService.createEducationInformation(educationInformationDto, basicInfoId))
                , HttpStatus.CREATED);
    }



    @PutMapping("/update/{edu-info-id}")
    public ResponseEntity<GlobalApiResponse> updateEducationInformation(@Valid @RequestBody EducationInformationDto educationInformationDto,
                                                                              @PathVariable("edu-info-id") Short educationInfoId){
        educationInformationService.updateEducationInformation(educationInformationDto,educationInfoId);
        return ResponseEntity.ok(successResponse(customMessageSource.get(MessageConstant.CRUD_UPDATE,customMessageSource
                .get(MessageCodeConstant.EDUCATION_INFORMATION)),null));

    }



    @DeleteMapping("/delete/{edu-info-id}")
    public ResponseEntity<GlobalApiResponse>deleteEducationInfo(@PathVariable("edu-info-id") Short educationInfoId){
        if (educationInfoId==null){
            throw new NullPointerException(MessageConstant.ID_NULL);
        }
        educationInformationService.deleteEducationInformation(educationInfoId);
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_DELETE,customMessageSource
                        .get(MessageCodeConstant.EDUCATION_INFORMATION)),"Id:"+educationInfoId),HttpStatus.OK);

    }


    @GetMapping("/find-all-edu-info")
    public ResponseEntity<GlobalApiResponse> getAllEducationInformation(){
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_GET_ALL,customMessageSource
                .get(messageCode)),educationInformationService.getAllEducationInformation()),HttpStatus.OK);
    }

    @GetMapping("/find/{edu-info-id}")
    public ResponseEntity<GlobalApiResponse> getSingleEducationInformation(@PathVariable("edu-info-id") Short educationInfoId){
        if (educationInfoId==null){
            throw new NullPointerException(MessageConstant.ID_NULL);
        }
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_GET,customMessageSource.get(MessageCodeConstant.EDUCATION_INFORMATION)),educationInformationService.getEducationInformationById(educationInfoId)),HttpStatus.OK);
    }

}
