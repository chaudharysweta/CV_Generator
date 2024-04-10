package com.example.cv_generator.service;

import com.example.cv_generator.dto.LocalLevelDto;

import java.util.List;

public interface LocalLevelService {

    //create
    LocalLevelDto createLocalLevel(LocalLevelDto localLevelDto,Integer districtId);

    //update
    LocalLevelDto updateLocalLevel(LocalLevelDto localLevelDto,Integer localId);

    //delete
    void  deleteLocalLevel(Integer localId);

    //get all
    List<LocalLevelDto> getAllLocalLevel();

    //get by id
    LocalLevelDto getLocalDistrictById(Integer localId);

}
