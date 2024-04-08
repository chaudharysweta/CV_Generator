package com.example.cv_generator.service.Impl;

import com.example.cv_generator.dto.ProjectInformationDto;
import com.example.cv_generator.entity.ProjectInformation;
import com.example.cv_generator.exception.ResourceNotFoundException;
import com.example.cv_generator.repository.ProjectInformationRepository;
import com.example.cv_generator.repository.ProvinceRepository;
import com.example.cv_generator.service.ProjectInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectInformationServiceImpl implements ProjectInformationService {

    private final ProjectInformationRepository projectInformationRepository;
    private final ModelMapper modelMapper;

    public ProjectInformationServiceImpl(ProvinceRepository provinceRepository, ProjectInformationRepository projectInformationRepository, ModelMapper modelMapper) {
        this.projectInformationRepository = projectInformationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProjectInformationDto createProjectInformation(ProjectInformationDto projectInformationDto) {
        ProjectInformation projectInformation=modelMapper.map(projectInformationDto,ProjectInformation.class);
        ProjectInformation createdProInfo =projectInformationRepository.save(projectInformation);
        return modelMapper.map(createdProInfo,ProjectInformationDto.class);
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
}
