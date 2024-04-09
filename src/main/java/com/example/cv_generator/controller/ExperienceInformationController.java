package com.example.cv_generator.controller;

import com.example.cv_generator.dto.ApiResponse;
import com.example.cv_generator.dto.ExperienceInformationDto;
import com.example.cv_generator.service.BasicInformationService;
import com.example.cv_generator.service.ExperienceInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exp-info")
public class ExperienceInformationController {

    private final ExperienceInformationService experienceInformationService;



    public ExperienceInformationController(ExperienceInformationService experienceInformationService) {
        this.experienceInformationService = experienceInformationService;
    }

    @PostMapping("/create/{basic-info-id}")
    public ResponseEntity<ExperienceInformationDto> createExpInfo(@RequestBody ExperienceInformationDto experienceInformationDto,@PathVariable("basic-info-id") Short basicInfoId){
        ExperienceInformationDto experienceInformationDto1=experienceInformationService.createExpInfo(experienceInformationDto,basicInfoId);
        return new ResponseEntity<>(experienceInformationDto1, HttpStatus.CREATED);
    }
    @PutMapping("/update/{exp-info-id}")
    public ResponseEntity<ExperienceInformationDto> updateExpInfo(@RequestBody ExperienceInformationDto experienceInformationDto, @PathVariable("exp-info-id") Short expInfoId) {
        ExperienceInformationDto updatedExperienceInformationDto = experienceInformationService.updateExpInfo(experienceInformationDto, expInfoId);
        return ResponseEntity.ok(updatedExperienceInformationDto);
    }




    @DeleteMapping("/delete/{exp-info-id}")
    public ResponseEntity<ApiResponse> deteteExpInfo(@PathVariable("exp-info-id") Short expInfoId){
        experienceInformationService.deleteExpInfo(expInfoId);
        return new ResponseEntity<>(new ApiResponse("Experience Information Deleted Successfully",true),HttpStatus.OK);
    }

    //get all author
    @GetMapping("/get-all-exp")
    public ResponseEntity<List<ExperienceInformationDto>> getAllAuthors(){
        return ResponseEntity.ok(experienceInformationService.getAllExpInfo());
    }
    //get by id
    @GetMapping("/{exp-info-id}")
    public ResponseEntity<ExperienceInformationDto>getSingleAuthor(@PathVariable("exp-info-id") Short expInfoId){
        ExperienceInformationDto experienceInformationDto=experienceInformationService.getExpInfoById(expInfoId);
        return new ResponseEntity<>(experienceInformationDto,HttpStatus.OK);
    }
}
