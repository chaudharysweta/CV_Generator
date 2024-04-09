package com.example.cv_generator.service;

import com.example.cv_generator.dto.ProjectInformationDto;

import java.util.List;

public interface ProjectInformationService {

    //create
    ProjectInformationDto createProjectInformation(ProjectInformationDto projectInformationDto, Short expInfoId);

    //update
    ProjectInformationDto updateProjectInformation(ProjectInformationDto projectInformationDto,Short proInfoId);

    //delete
    void deleteProjectInformation(Short proInfoId);

    //get all
    List<ProjectInformationDto> getAllProjectInformation();

    //get by id
    ProjectInformationDto getProjectInformationById(Short proInfoId);
}
