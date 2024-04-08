package com.example.cv_generator.service.Impl;

import com.example.cv_generator.dto.EducationInformationDto;
import com.example.cv_generator.dto.ExperienceInformationDto;
import com.example.cv_generator.entity.ExperienceInformation;
import com.example.cv_generator.exception.ResourceNotFoundException;
import com.example.cv_generator.repository.ExperienceInformationRepository;
import com.example.cv_generator.service.ExperienceInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceInformationServiceImpl implements ExperienceInformationService {

    private final ExperienceInformationRepository experienceInformationRepository;
    private final ModelMapper modelMapper;

    public ExperienceInformationServiceImpl(ExperienceInformationRepository experienceInformationRepository, ModelMapper modelMapper) {
        this.experienceInformationRepository = experienceInformationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ExperienceInformationDto createExpInfo(ExperienceInformationDto experienceInformationDto) {
        ExperienceInformation experienceInformation=modelMapper.map(experienceInformationDto,ExperienceInformation.class);
        ExperienceInformation createdExpInfo = experienceInformationRepository.save(experienceInformation);
        return modelMapper.map(createdExpInfo,ExperienceInformationDto.class);
    }

    @Override
    public ExperienceInformationDto updateExpInfo(ExperienceInformationDto experienceInformationDto, Short expInfoId) {
        ExperienceInformation experienceInformation=experienceInformationRepository.findById(expInfoId).orElseThrow(()->new ResourceNotFoundException("Experience Information ","Id",expInfoId));
        experienceInformation.setCompanyName(experienceInformationDto.getCompanyName());
        experienceInformation.setCompanyAddress(experienceInformationDto.getCompanyAddress());
        experienceInformation.setCompanyContact(experienceInformationDto.getCompanyContact());
        experienceInformation.setFromDate(experienceInformationDto.getFromDate());
        experienceInformation.setToDate(experienceInformationDto.getToDate());
        experienceInformation.setToPresent(experienceInformationDto.isToPresent());
        ExperienceInformation updatedExpInfo = experienceInformationRepository.save(experienceInformation);
        return modelMapper.map(updatedExpInfo,ExperienceInformationDto.class);
    }

    @Override
    public void deleteExpInfo(Short expInfoId) {
        ExperienceInformation experienceInformation=experienceInformationRepository.findById(expInfoId).orElseThrow(()->new ResourceNotFoundException("Experience Information ","Id",expInfoId));
        experienceInformationRepository.delete(experienceInformation);

    }

    @Override
    public List<ExperienceInformationDto> getAllExpInfo() {
        List<ExperienceInformation> experienceInformations=experienceInformationRepository.findAll();
        List<ExperienceInformationDto> experienceInformationDtos=experienceInformations.stream().map((expInfo)->modelMapper.map(expInfo, ExperienceInformationDto.class)).toList();
        return experienceInformationDtos;
    }

    @Override
    public ExperienceInformationDto getExpInfoById(Short expInfoId) {
        ExperienceInformation experienceInformation=experienceInformationRepository.findById(expInfoId).orElseThrow(()->new ResourceNotFoundException("Experience Information ","Id",expInfoId));
        return modelMapper.map(experienceInformation,ExperienceInformationDto.class);
    }
}
