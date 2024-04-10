package com.example.cv_generator.service;

import com.example.cv_generator.dto.DistrictDto;

import java.util.List;

public interface DistrictService {

    //create
    DistrictDto createDistrict(DistrictDto districtDto, Integer provinceId);

    //update
    DistrictDto updateDistrict(DistrictDto districtDto,Integer districtId);

    //delete
    void deleteDistrict(Integer districtId);

    //get all
    List<DistrictDto> getAllDistrict();

    //get all by id
    DistrictDto getDistrictById(Integer districtId);

}
