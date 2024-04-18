package com.example.cv_generator.service.Impl;

import com.example.cv_generator.dto.BasicInformationDto;
import com.example.cv_generator.dto.ExperienceInformationDto;
import com.example.cv_generator.entity.BasicInformation;
import com.example.cv_generator.entity.ExperienceInformation;
import com.example.cv_generator.exception.ResourceNotFoundException;
import com.example.cv_generator.repository.BasicInformationRepository;
import com.example.cv_generator.repository.ExperienceInformationRepository;
import com.example.cv_generator.service.ExperienceInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExperienceInformationServiceImpl implements ExperienceInformationService {

    private final ExperienceInformationRepository experienceInformationRepository;
    private final ModelMapper modelMapper;

    private final BasicInformationRepository basicInformationRepository;

    public ExperienceInformationServiceImpl(ExperienceInformationRepository experienceInformationRepository, ModelMapper modelMapper, BasicInformationRepository basicInformationRepository) {
        this.experienceInformationRepository = experienceInformationRepository;
        this.modelMapper = modelMapper;
        this.basicInformationRepository = basicInformationRepository;
    }



    @Override
    public ExperienceInformationDto createExpInfo(ExperienceInformationDto experienceInformationDto, Short basicInfoId) {
        ExperienceInformation experienceInformation = dtoToExpInfo(experienceInformationDto, basicInfoId);
        ExperienceInformation savedExpInfo = experienceInformationRepository.save(experienceInformation);
        return expInfoToDto(savedExpInfo);

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

    @Override
    public List<ExperienceInformationDto> getExperienceInfoByBasicInfoId(Short basicInfoId) {
        return toDto(experienceInformationRepository.findExperienceInformationByBasicInformationId((basicInfoId)));
    }


    public List<ExperienceInformationDto> toDto(List<ExperienceInformation> experienceInformationList) {
        return experienceInformationList.stream().map(this::expInfoToDto).collect(Collectors.toList());
    }


    public ExperienceInformation dtoToExpInfo(ExperienceInformationDto experienceInformationDto, Short basicInfoId) {
        BasicInformation basicInformation = basicInformationRepository.findById(basicInfoId)
                .orElseThrow(() -> new ResourceNotFoundException("Basic Information", "Id", basicInfoId));
        ExperienceInformation experienceInformation = new ExperienceInformation();
        experienceInformation.setCompanyName(experienceInformationDto.getCompanyName());
        experienceInformation.setCompanyAddress(experienceInformationDto.getCompanyAddress());
        experienceInformation.setCompanyContact(experienceInformationDto.getCompanyContact());
        experienceInformation.setFromDate(experienceInformationDto.getFromDate());
        experienceInformation.setToDate(experienceInformationDto.getToDate());
        experienceInformation.setToPresent(experienceInformationDto.isToPresent());
        experienceInformation.setBasicInformation(basicInformation);
        return experienceInformation;
    }

    public ExperienceInformationDto expInfoToDto(ExperienceInformation experienceInformation) {
        BasicInformationDto basicInformationDto = new BasicInformationDto();
        basicInformationDto.setId(experienceInformation.getBasicInformation().getId());
        basicInformationDto.setFirstName(experienceInformation.getBasicInformation().getFirstName());
        basicInformationDto.setMiddleName(experienceInformation.getBasicInformation().getMiddleName());
        basicInformationDto.setLast_name(experienceInformation.getBasicInformation().getLast_name());
        basicInformationDto.setBackground(experienceInformation.getBasicInformation().getBackground());
        basicInformationDto.setTitle(experienceInformation.getBasicInformation().getTitle());
        basicInformationDto.setLinkedInUrl(experienceInformation.getBasicInformation().getLinkedInUrl());
        basicInformationDto.setMobileNumber(experienceInformation.getBasicInformation().getMobileNumber());
        basicInformationDto.setProfileImage(experienceInformation.getBasicInformation().getProfileImage());

        ExperienceInformationDto experienceInformationDto = new ExperienceInformationDto();
        experienceInformationDto.setCompanyName(experienceInformation.getCompanyName());
        experienceInformationDto.setCompanyAddress(experienceInformation.getCompanyAddress());
        experienceInformationDto.setCompanyContact(experienceInformation.getCompanyContact());
        experienceInformationDto.setFromDate(experienceInformation.getFromDate());
        experienceInformationDto.setToDate(experienceInformation.getToDate());
        experienceInformationDto.setToPresent(experienceInformation.isToPresent());
        experienceInformationDto.setBasicInformation(basicInformationDto);
        return experienceInformationDto;
    }

}
