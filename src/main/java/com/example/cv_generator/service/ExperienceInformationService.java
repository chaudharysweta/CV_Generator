package com.example.cv_generator.service;

import com.example.cv_generator.dto.ExperienceInformationDto;

import java.util.List;

public interface ExperienceInformationService {

    //create
    ExperienceInformationDto createExpInfo(ExperienceInformationDto experienceInformationDto,Short basicInfoId);

    //update
    ExperienceInformationDto updateExpInfo(ExperienceInformationDto experienceInformationDto,Short expInfoId);

    //delete
    void deleteExpInfo(Short expInfoId);

    //get all
    List<ExperienceInformationDto> getAllExpInfo();

    //get  by id
    ExperienceInformationDto getExpInfoById(Short expInfoId);
}
