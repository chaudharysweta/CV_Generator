package com.example.cv_generator.controller;

import com.example.cv_generator.dto.ApiResponse;
import com.example.cv_generator.dto.EducationInformationDto;
import com.example.cv_generator.service.EducationInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edu-info")
public class EducationInformationController {

    private final EducationInformationService educationInformationService;

    public EducationInformationController(EducationInformationService educationInformationService) {
        this.educationInformationService = educationInformationService;
    }

    @PostMapping("/createEdu/{basic-info-id}")
    public ResponseEntity<EducationInformationDto> createEduInfo(@RequestBody EducationInformationDto educationInformationDto,@PathVariable("basic-info-id") Short basicInfoId){
        EducationInformationDto educationInformationDto1=educationInformationService.createEducationInformation(educationInformationDto,basicInfoId);
        return new ResponseEntity<>(educationInformationDto1, HttpStatus.CREATED);
    }


    @PutMapping("/update/{edu-info-id}")
    public ResponseEntity<EducationInformationDto> updateEducationInformation(@RequestBody EducationInformationDto educationInformationDto,@PathVariable("edu-info-id") Short educationInfoId){
        EducationInformationDto educationInformationDto1=this.educationInformationService.updateEducationInformation(educationInformationDto,educationInfoId);
        return ResponseEntity.ok(educationInformationDto1);
    }

    @DeleteMapping("/delete/{edu-info-id}")
    public ResponseEntity<ApiResponse>deleteEducationInfo(@PathVariable("edu-info-id") Short educationInfoId){
        educationInformationService.deleteEducationInformation(educationInfoId);
        return new ResponseEntity<>(new ApiResponse("Education Information Deleted Successfully",true),HttpStatus.OK);
    }

    @GetMapping("/find-all-edu-info")
    public ResponseEntity<List<EducationInformationDto>> getAllEducationInformation(){
        return ResponseEntity.ok(this.educationInformationService.getAllEducationInformation());
    }

    @GetMapping("/find/{edu-info-id}")
    public ResponseEntity<EducationInformationDto> getSingleEducationInformation(@PathVariable("edu-info-id") Short educationInfoId){
        return ResponseEntity.ok(this.educationInformationService.getEducationInformationById(educationInfoId));
    }
}
