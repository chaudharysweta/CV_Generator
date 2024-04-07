package com.example.cv_generator.service;

import com.example.cv_generator.dto.BasicInformationDto;

import java.util.List;

public interface BasicInformationService {

    //create
    BasicInformationDto createBasicInformation(BasicInformationDto basicInformationDto);

    //update
    BasicInformationDto updateBasicInformation(BasicInformationDto basicInformationDto,Short basicInfoId);

    //delete
    void deleteBasicInformation(Short basicInfoId);

    //get all
    List<BasicInformationDto> getAllBasicInformation();

    //get by id
    BasicInformationDto getBasicInformationById(Short basicInfoId);
}
