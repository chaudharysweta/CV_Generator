package com.example.cv_generator.controller;

import com.example.cv_generator.dto.ApiResponse;
import com.example.cv_generator.dto.BasicInformationDto;
import com.example.cv_generator.service.BasicInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basic-info")
public class BasicInformationController {

    private final BasicInformationService basicInformationService;

    public BasicInformationController(BasicInformationService basicInformationService) {
        this.basicInformationService = basicInformationService;
    }

    @PostMapping("/create")
    public ResponseEntity<BasicInformationDto> createBasicInformation(@RequestBody BasicInformationDto basicInformationDto){
        BasicInformationDto basicInformationDto1=basicInformationService.createBasicInformation(basicInformationDto);
        return new ResponseEntity<>(basicInformationDto1, HttpStatus.CREATED);
    }

    @PutMapping("/update/{basic-info-id}")
    public ResponseEntity<BasicInformationDto> updateBasicInformation(@RequestBody BasicInformationDto basicInformationDto,@PathVariable("basic-info-id") Short basicInfoId){
        BasicInformationDto basicInformationDto1 = this.basicInformationService.updateBasicInformation(basicInformationDto,basicInfoId);
        return ResponseEntity.ok(basicInformationDto1);
    }

    @DeleteMapping("/delete/{basic-info-id}")
    public ResponseEntity<ApiResponse> deleteBasicInformation(@PathVariable Short basicInfoId){
        this.basicInformationService.deleteBasicInformation(basicInfoId);
        return new ResponseEntity<>(new ApiResponse("Basic Information Deleted Successfully",true),HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<BasicInformationDto>>getAllBasicInfo(){
        return ResponseEntity.ok(this.basicInformationService.getAllBasicInformation());
    }

    @GetMapping("/find/{basic-info-id}")
    public ResponseEntity<BasicInformationDto> getSingleBasicInfo(@PathVariable("basic-info-id") Short basicInfoId){
        return ResponseEntity.ok(this.basicInformationService.getBasicInformationById(basicInfoId));
    }
}
