package com.example.cv_generator.controller;

import com.example.cv_generator.dto.ApiResponse;
import com.example.cv_generator.dto.ProjectInformationDto;
import com.example.cv_generator.service.ProjectInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pro-info")
public class ProjectInformationController {

    private final ProjectInformationService projectInformationService;

    public ProjectInformationController(ProjectInformationService projectInformationService) {
        this.projectInformationService = projectInformationService;
    }

    //create
    @PostMapping("/create")
    public ResponseEntity<ProjectInformationDto> createProInfo(@RequestBody ProjectInformationDto projectInformationDto){
        ProjectInformationDto projectInformationDto1=projectInformationService.createProjectInformation(projectInformationDto);
        return new ResponseEntity<>(projectInformationDto1, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/update/{pro-info-id}")
    public ResponseEntity<ProjectInformationDto> updateProInfo(@RequestBody ProjectInformationDto projectInformationDto,@PathVariable("pro-info-id") Short proInfoId){
        ProjectInformationDto projectInformationDto1=projectInformationService.updateProjectInformation(projectInformationDto,proInfoId);
        return ResponseEntity.ok(projectInformationDto1);
    }

    //delete
    @DeleteMapping("/delete/{pro-info-id}")
    public ResponseEntity<ApiResponse> deleteProInfo(@PathVariable("pro-info-id") Short proInfoId){
        projectInformationService.deleteProjectInformation(proInfoId);
        return new ResponseEntity<>(new ApiResponse("Project Information Deleted Successfully",true),HttpStatus.OK);
    }

    //get all
    @GetMapping("/get-all-pro-info")
    public ResponseEntity<List<ProjectInformationDto>> getAllProInfo(){
        return ResponseEntity.ok(projectInformationService.getAllProjectInformation());
    }

    //get all by id
    @GetMapping("/get/{pro-info-id}")
    public ResponseEntity<ProjectInformationDto> getSingleProInfo(@PathVariable("pro-info-id") Short proInfoId){
        return ResponseEntity.ok(projectInformationService.getProjectInformationById(proInfoId));
    }
}
