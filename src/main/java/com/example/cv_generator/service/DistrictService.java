package com.example.cv_generator.service;

import com.example.cv_generator.dto.DistrictDto;

import java.util.List;

public interface DistrictService {

    //create
    DistrictDto createDistrict(DistrictDto districtDto, Short provinceId);

    //update
    DistrictDto updateDistrict(DistrictDto districtDto,Short districtId);

    //delete
    void deleteDistrict(Short districtId);

    //get all
    List<DistrictDto> getAllDistrict();

    //get all by id
    DistrictDto getDistrictById(Short districtId);

}
