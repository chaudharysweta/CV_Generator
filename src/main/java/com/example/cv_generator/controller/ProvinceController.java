package com.example.cv_generator.controller;

import com.example.cv_generator.config.MessageCodeConstant;
import com.example.cv_generator.config.MessageConstant;
import com.example.cv_generator.dto.ApiResponse;
import com.example.cv_generator.dto.CustomMessageSource;
import com.example.cv_generator.dto.GlobalApiResponse;
import com.example.cv_generator.dto.ProvinceDto;
import com.example.cv_generator.service.ProvinceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/province")
public class ProvinceController extends BaseController{

    private final ProvinceService provinceService;
    private final CustomMessageSource customMessageSource;

    public ProvinceController(ProvinceService provinceService, CustomMessageSource customMessageSource) {
        this.provinceService = provinceService;
        this.customMessageSource = customMessageSource;
        this.messageCode= MessageCodeConstant.EDUCATION_INFORMATION;
    }

    //create
    @PostMapping("create/{country-id}")
    public ResponseEntity<GlobalApiResponse> createProvince(@RequestBody ProvinceDto provinceDto,
                                                            @PathVariable("country-id") Short countryId){
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_CREATE,customMessageSource
                .get(messageCode)),provinceService.createdProvince(provinceDto,countryId)),HttpStatus.OK);
    }

    //update
    @PutMapping("/update/{province-id}")
    public ResponseEntity<ProvinceDto> updateProvince(@RequestBody ProvinceDto provinceDto,
                                                      @PathVariable("province-id") Short provinceId){
        ProvinceDto provinceDto1=provinceService.updateProvince(provinceDto,provinceId);
        return ResponseEntity.ok(provinceDto1);
    }



    //delete
    @DeleteMapping("/delete/{province-id}")
    public ResponseEntity<ApiResponse> deleteProvince(@PathVariable("province-id") Short provinceId){
        provinceService.deleteProvince(provinceId);
        return new ResponseEntity<>(new ApiResponse("Province Deleted Successfully",true),HttpStatus.OK);
    }

    //get all
    @GetMapping("/get-all-province")
    public ResponseEntity<GlobalApiResponse> getAllProvince(){
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_GET_ALL,customMessageSource
                .get(messageCode)),provinceService.getAllProvince()),HttpStatus.OK);
    }
    //get by id
    @GetMapping("/{province-id}")
    public ResponseEntity<GlobalApiResponse> getProvinceById(@PathVariable("province-id") Short provinceId){
        if (provinceId==null){
            throw new NullPointerException(MessageConstant.ID_NULL);
        }
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_GET,customMessageSource.get(MessageCodeConstant.PROVINCE)),provinceService.getProvinceById(provinceId)),HttpStatus.OK);
    }
}
