package com.example.cv_generator.service;

import com.example.cv_generator.dto.ProvinceDto;

import java.util.List;

public interface ProvinceService {

    //create
    ProvinceDto createdProvince(ProvinceDto provinceDto,Integer countryId);

    //update
    ProvinceDto updateProvince(ProvinceDto provinceDto,Integer provinceId);

    //delete
    void deleteProvince(Integer provinceId);

    //get all
    List<ProvinceDto> getAllProvince();

    //get  by id
    ProvinceDto getProvinceById(Integer provinceId);
}



