package com.example.cv_generator.controller;

import com.example.cv_generator.dto.ApiResponse;
import com.example.cv_generator.dto.ProvinceDto;
import com.example.cv_generator.service.ProvinceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/province")
public class ProvinceController {

    private final ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    //create
    @PostMapping("create/{country-id}")
    public ResponseEntity<ProvinceDto> createProvince(@RequestBody ProvinceDto provinceDto, @PathVariable("country-id") Integer countryId){
        ProvinceDto provinceDto1=provinceService.createdProvince(provinceDto,countryId);
        return new ResponseEntity<>(provinceDto1, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/update/{province-id}")
    public ResponseEntity<ProvinceDto> updateProvince(@RequestBody ProvinceDto provinceDto,@PathVariable("province-id") Integer provinceId){
        ProvinceDto provinceDto1=provinceService.updateProvince(provinceDto,provinceId);
        return ResponseEntity.ok(provinceDto1);
    }

    //delete
    @DeleteMapping("/delete/{province-id}")
    public ResponseEntity<ApiResponse> deleteProvince(@PathVariable("province-id") Integer provinceId){
        provinceService.deleteProvince(provinceId);
        return new ResponseEntity<>(new ApiResponse("Province Deleted Successfully",true),HttpStatus.OK);
    }

    //get all
    @GetMapping("/get-all-province")
    public ResponseEntity<List<ProvinceDto>> getAllProvince(){
        return ResponseEntity.ok(provinceService.getAllProvince());
    }

    //get by id
    @GetMapping("/{province-id}")
    public ResponseEntity<ProvinceDto> getProvinceById(@PathVariable("province-id") Integer provinceId){
        ProvinceDto provinceDto= provinceService.getProvinceById(provinceId);
        return new ResponseEntity<>(provinceDto,HttpStatus.OK);
    }

}
