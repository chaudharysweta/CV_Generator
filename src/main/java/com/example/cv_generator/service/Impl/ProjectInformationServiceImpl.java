package com.example.cv_generator.service.Impl;

import com.example.cv_generator.dto.ExperienceInformationDto;
import com.example.cv_generator.dto.ProjectInformationDto;
import com.example.cv_generator.entity.ExperienceInformation;
import com.example.cv_generator.entity.ProjectInformation;
import com.example.cv_generator.exception.ResourceNotFoundException;
import com.example.cv_generator.repository.ExperienceInformationRepository;
import com.example.cv_generator.repository.ProjectInformationRepository;
import com.example.cv_generator.repository.ProvinceRepository;
import com.example.cv_generator.service.ProjectInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectInformationServiceImpl implements ProjectInformationService {

    private final ProjectInformationRepository projectInformationRepository;
    private final ModelMapper modelMapper;

    private final ExperienceInformationRepository experienceInformationRepository;

    public ProjectInformationServiceImpl(ProjectInformationRepository projectInformationRepository, ModelMapper modelMapper, ExperienceInformationRepository experienceInformationRepository) {
        this.projectInformationRepository = projectInformationRepository;
        this.modelMapper = modelMapper;
        this.experienceInformationRepository = experienceInformationRepository;
    }

    @Override
    public ProjectInformationDto createProjectInformation(ProjectInformationDto projectInformationDto,Short expInfoId) {
        ProjectInformation projectInformation=dtoToProjectInfo(projectInformationDto,expInfoId);
        ProjectInformation saveProjectInfo=projectInformationRepository.save(projectInformation);
        return projectInfoToDto(saveProjectInfo);
    }



    @Override
    public ProjectInformationDto updateProjectInformation(ProjectInformationDto projectInformationDto, Short proInfoId) {
        ProjectInformation projectInformation=projectInformationRepository.findById(proInfoId).orElseThrow(()->new ResourceNotFoundException("Project Information","Id",proInfoId));
        projectInformation.setProjectName(projectInformationDto.getProjectName());
        projectInformation.setProjectStatus(projectInformationDto.getProjectStatus());
        projectInformation.setProjectRole(projectInformationDto.getProjectRole());
        projectInformation.setProjectDescription(projectInformationDto.getProjectDescription());
        projectInformation.setTechStack(projectInformationDto.getTechStack());
        projectInformation.setProjectUrl(projectInformationDto.getProjectUrl());
        ProjectInformation updatedProjectInfo=projectInformationRepository.save(projectInformation);
        return modelMapper.map(updatedProjectInfo,ProjectInformationDto.class);

    }

    @Override
    public void deleteProjectInformation(Short proInfoId) {

        ProjectInformation projectInformation=projectInformationRepository.findById(proInfoId).orElseThrow(()->new ResourceNotFoundException("Project Information","Id",proInfoId));
        projectInformationRepository.delete(projectInformation);

    }

    @Override
    public List<ProjectInformationDto> getAllProjectInformation() {
        List<ProjectInformation> projectInformations = projectInformationRepository.findAll();
        List<ProjectInformationDto> projectInformationDtos = projectInformations.stream().map((proInfo)->modelMapper.map(proInfo,ProjectInformationDto.class)).toList();
        return projectInformationDtos;
    }

    @Override
    public ProjectInformationDto getProjectInformationById(Short proInfoId) {
        ProjectInformation projectInformation=projectInformationRepository.findById(proInfoId).orElseThrow(()->new ResourceNotFoundException("Project Information","Id",proInfoId));
        return modelMapper.map(projectInformation,ProjectInformationDto.class);
    }

    @Override
    public List<ProjectInformationDto> getProjectInfoByExperienceInfoId(Short experienceInfoId) {

        return  toDto(projectInformationRepository.findProjectInformationByExperienceInformationId(experienceInfoId));
    }

    @Override
    public List<ProjectInformationDto> getProjectInfoByBasicInfoId(Short basicInfoId) {
        return toDto(projectInformationRepository.findByExperienceInformation_BasicInformation_Id(basicInfoId));
    }

    public List<ProjectInformationDto> toDto(List<ProjectInformation> projectInformationList){
        return projectInformationList.stream().map(this::projectInfoToDto).collect(Collectors.toList());
    }

    public ProjectInformation dtoToProjectInfo(ProjectInformationDto projectInformationDto,Short expInfoId){
        ExperienceInformation experienceInformation=experienceInformationRepository.findById(expInfoId)
                .orElseThrow(() -> new ResourceNotFoundException("Project Information", "Id", expInfoId));
        ProjectInformation projectInformation=new ProjectInformation();
        projectInformation.setProjectName(projectInformationDto.getProjectName());
        projectInformation.setProjectStatus(projectInformationDto.getProjectStatus());
        projectInformation.setProjectRole(projectInformationDto.getProjectRole());
        projectInformation.setProjectDescription(projectInformationDto.getProjectDescription());
        projectInformation.setTechStack(projectInformationDto.getTechStack());
        projectInformation.setProjectUrl(projectInformationDto.getProjectUrl());
        projectInformation.setExperienceInformation(experienceInformation);
        return projectInformation;
    }

    public ProjectInformationDto projectInfoToDto(ProjectInformation projectInformation){
        ExperienceInformationDto experienceInformationDto=new ExperienceInformationDto();
        experienceInformationDto.setId(projectInformation.getExperienceInformation().getId());

        ProjectInformationDto projectInformationDto=new ProjectInformationDto();
        projectInformationDto.setProjectName(projectInformation.getProjectName());
        projectInformationDto.setProjectStatus(projectInformation.getProjectStatus());
        projectInformationDto.setProjectRole(projectInformation.getProjectRole());
        projectInformationDto.setProjectDescription(projectInformation.getProjectDescription());
        projectInformationDto.setTechStack(projectInformation.getTechStack());
        projectInformationDto.setProjectUrl(projectInformation.getProjectUrl());
        return projectInformationDto;
    }



}
