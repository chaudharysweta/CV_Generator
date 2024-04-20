package com.example.cv_generator.service.Impl;

import com.example.cv_generator.dto.BasicInformationDto;
import com.example.cv_generator.entity.BasicInformation;
import com.example.cv_generator.exception.ResourceNotFoundException;
import com.example.cv_generator.repository.*;
import com.example.cv_generator.service.BasicInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicInformationServiceImpl implements BasicInformationService {

    private final BasicInformationRepository basicInformationRepository;


    private final ModelMapper modelMapper;

    public BasicInformationServiceImpl(BasicInformationRepository basicInformationRepository, ModelMapper modelMapper) {
        this.basicInformationRepository = basicInformationRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public BasicInformationDto createBasicInformation(BasicInformationDto basicInformationDto) {
        BasicInformation basicInformation=modelMapper.map(basicInformationDto,BasicInformation.class);
        BasicInformation createdBasicInfo=basicInformationRepository.save(basicInformation);
        return modelMapper.map(createdBasicInfo,BasicInformationDto.class);
    }

    @Override
    public BasicInformationDto updateBasicInformation(BasicInformationDto basicInformationDto, Short basicInfoId) {
        BasicInformation basicInformation=basicInformationRepository.findById(basicInfoId).orElseThrow(()->new ResourceNotFoundException("Basic Information ","Id",basicInfoId));
        basicInformation.setFirstName(basicInformationDto.getFirstName());
        basicInformation.setMiddleName(basicInformationDto.getMiddleName());
        basicInformation.setLast_name(basicInformationDto.getLast_name());
        basicInformation.setBackground(basicInformationDto.getBackground());
        basicInformation.setTitle(basicInformationDto.getTitle());
        basicInformation.setMobileNumber(basicInformationDto.getMobileNumber());
        basicInformation.setEmail(basicInformationDto.getEmail());
        basicInformation.setLinkedInUrl(basicInformationDto.getLinkedInUrl());
        basicInformation.setProfileImage(basicInformationDto.getProfileImage());
        BasicInformation updatedBasicInformation=basicInformationRepository.save(basicInformation);
        return modelMapper.map(updatedBasicInformation,BasicInformationDto.class);
    }

    @Override
    public void deleteBasicInformation(Short basicInfoId) {
        BasicInformation basicInformation=basicInformationRepository.findById(basicInfoId).orElseThrow(()->new ResourceNotFoundException("Basic Information ","Id",basicInfoId));
        basicInformationRepository.delete(basicInformation);

    }

    @Override
    public List<BasicInformationDto> getAllBasicInformation() {
        List<BasicInformation> basicInformations = basicInformationRepository.findAll();
        List<BasicInformationDto> basicInformationDtos=basicInformations.stream().map((basicInfo)->modelMapper.map(basicInfo,BasicInformationDto.class)).toList();
        return basicInformationDtos;
    }

    @Override
    public BasicInformationDto getBasicInformationById(Short basicInfoId) {
        BasicInformation basicInformation=basicInformationRepository.findById(basicInfoId).orElseThrow(()->new ResourceNotFoundException("Basic Information ","Id",basicInfoId));
        return modelMapper.map(basicInformation,BasicInformationDto.class);
    }

}
