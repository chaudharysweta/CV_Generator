package com.example.cv_generator.service;

import com.example.cv_generator.dto.LocalLevelDto;
import com.example.cv_generator.dto.ProjectInformationDto;

import java.util.List;

public interface LocalLevelService {

    //create
    LocalLevelDto createLocalLevel(LocalLevelDto localLevelDto,Short districtId);

    //update
    LocalLevelDto updateLocalLevel(LocalLevelDto localLevelDto,Short localId);

    //delete
    void  deleteLocalLevel(Short localId);

    //get all
    List<LocalLevelDto> getAllLocalLevel();

    //get by id
    LocalLevelDto getLocalDistrictById(Short localId);

//    List<LocalLevelDto> getLocalInfoByAddressInfoId(Short experienceInfoId);
//    // get by basic id
//    List<LocalLevelDto> getLocalInfoByBasicInfoId(Short basicInfoId);



}
