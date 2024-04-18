package com.example.cv_generator.service;

import com.example.cv_generator.dto.ProvinceDto;

import java.util.List;

public interface ProvinceService {

    //create
    ProvinceDto createdProvince(ProvinceDto provinceDto,Short countryId);

    //update
    ProvinceDto updateProvince(ProvinceDto provinceDto,Short provinceId);

    //delete
    void deleteProvince(Short provinceId);

    //get all
    List<ProvinceDto> getAllProvince();

    //get  by id
    ProvinceDto getProvinceById(Short provinceId);
}



