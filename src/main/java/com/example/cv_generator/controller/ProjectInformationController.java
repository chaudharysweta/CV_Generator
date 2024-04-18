package com.example.cv_generator.controller;

import com.example.cv_generator.config.MessageCodeConstant;
import com.example.cv_generator.config.MessageConstant;
import com.example.cv_generator.dto.ApiResponse;
import com.example.cv_generator.dto.CustomMessageSource;
import com.example.cv_generator.dto.GlobalApiResponse;
import com.example.cv_generator.dto.ProjectInformationDto;
import com.example.cv_generator.service.ProjectInformationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pro-info")
public class ProjectInformationController extends BaseController{

    private final ProjectInformationService projectInformationService;

    private final CustomMessageSource customMessageSource;

    public ProjectInformationController(ProjectInformationService projectInformationService, CustomMessageSource customMessageSource) {
        this.projectInformationService = projectInformationService;
        this.customMessageSource = customMessageSource;
        this.messageCode= MessageCodeConstant.PROJECT_INFORMATION;
    }

    //create
    @PostMapping("/create/{exp-info-id}")
    public ResponseEntity<GlobalApiResponse> createProInfo(@Valid @RequestBody ProjectInformationDto projectInformationDto,
                                                           @PathVariable("exp-info-id") Short expInfoId){
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_CREATE,customMessageSource
                .get(messageCode)),projectInformationService.createProjectInformation(projectInformationDto, expInfoId)),HttpStatus.OK);
    }


    //update
    @PutMapping("/update/{pro-info-id}")
    public ResponseEntity<GlobalApiResponse> updateProInfo(@Valid @RequestBody ProjectInformationDto projectInformationDto,
                                                               @PathVariable("pro-info-id") Short proInfoId){
        projectInformationService.updateProjectInformation(projectInformationDto,proInfoId);
        return ResponseEntity.ok(successResponse(customMessageSource.get(MessageConstant.CRUD_UPDATE,customMessageSource
                .get(MessageCodeConstant.PROJECT_INFORMATION)),null));
    }
    //delete
    @DeleteMapping("/delete/{pro-info-id}")
    public ResponseEntity<ApiResponse> deleteProInfo(@PathVariable("pro-info-id") Short proInfoId){
        projectInformationService.deleteProjectInformation(proInfoId);
        return new ResponseEntity<>(new ApiResponse("Project Information Deleted Successfully",true),HttpStatus.OK);
    }

    //get all
    @GetMapping("/get-all-pro-info")
    public ResponseEntity<GlobalApiResponse> getAllProInfo(){
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_GET_ALL,customMessageSource
                .get(messageCode)),projectInformationService.getAllProjectInformation()),HttpStatus.OK);
    }



    //get all by id
    @GetMapping("/get/{pro-info-id}")
    public ResponseEntity<ProjectInformationDto> getSingleProInfo(@PathVariable("pro-info-id") Short proInfoId){
        return ResponseEntity.ok(projectInformationService.getProjectInformationById(proInfoId));
    }
}
