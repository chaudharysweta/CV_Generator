package com.example.cv_generator.service;

import com.example.cv_generator.dto.EducationInformationDto;

import java.util.List;

public interface EducationInformationService {

    //create
    EducationInformationDto createEducationInformation(EducationInformationDto educationInformationDto, Short basicInfoId);

    //update
    EducationInformationDto updateEducationInformation(EducationInformationDto educationInformationDto,Short educationInfoId);

    //delete
    void deleteEducationInformation(Short educationInfoId);

    //get all
    List<EducationInformationDto> getAllEducationInformation();

    //get all by id
    EducationInformationDto getEducationInformationById(Short educationInfoId);

    List<EducationInformationDto> getEducationByBasicId(Short basicInfoId);
}
