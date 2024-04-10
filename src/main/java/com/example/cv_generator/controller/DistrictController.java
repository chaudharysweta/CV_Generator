package com.example.cv_generator.controller;

import com.example.cv_generator.dto.ApiResponse;
import com.example.cv_generator.dto.DistrictDto;
import com.example.cv_generator.service.DistrictService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/district")
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @PostMapping("/create-dis/{province-id}")
    public ResponseEntity<DistrictDto> createDistrict(@RequestBody DistrictDto districtDto, @PathVariable("province-id") Integer provinceId){
        DistrictDto districtDto1=districtService.createDistrict(districtDto,provinceId);
        return new ResponseEntity<>(districtDto1, HttpStatus.CREATED);
    }

    @PutMapping("/update/{district-id}")
    public ResponseEntity<DistrictDto> updateDistrict(@RequestBody DistrictDto districtDto,@PathVariable("district-id") Integer districtId){
        DistrictDto districtDto1=districtService.updateDistrict(districtDto,districtId);
        return ResponseEntity.ok(districtDto1);
    }

    @DeleteMapping("/delete/{district-id}")
    public ResponseEntity<ApiResponse> deleteDistrict(@PathVariable("district-id") Integer districtId){
        districtService.deleteDistrict(districtId);
        return new ResponseEntity<>(new ApiResponse("District deleted successfully",true),HttpStatus.OK);
    }
    @GetMapping("/get-all-district")
    public ResponseEntity<List<DistrictDto>> getAllDistrict(){
        return ResponseEntity.ok(districtService.getAllDistrict());
    }
    @GetMapping("/{district-id}")
    public ResponseEntity<DistrictDto> getDistrictById(@PathVariable("district-id") Integer districtId){
        DistrictDto districtDto= districtService.getDistrictById(districtId);
        return new ResponseEntity<>(districtDto,HttpStatus.OK);
    }

}
