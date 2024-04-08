package com.example.cv_generator.service.Impl;

import com.example.cv_generator.dto.EducationInformationDto;
import com.example.cv_generator.entity.EducationInformation;
import com.example.cv_generator.exception.ResourceNotFoundException;
import com.example.cv_generator.repository.EducationInformationRepository;
import com.example.cv_generator.service.EducationInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationInformationServiceImpl implements EducationInformationService {

    private final EducationInformationRepository educationInformationRepository;
    private final ModelMapper modelMapper;

    public EducationInformationServiceImpl(EducationInformationRepository educationInformationRepository, ModelMapper modelMapper) {
        this.educationInformationRepository = educationInformationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EducationInformationDto createEducationInformation(EducationInformationDto educationInformationDto) {
            EducationInformation educationInformation=this.modelMapper.map(educationInformationDto,EducationInformation.class);
            EducationInformation createdEducationInformation=this.educationInformationRepository.save(educationInformation);
            return this.modelMapper.map(createdEducationInformation,EducationInformationDto.class);

    }

    @Override
    public EducationInformationDto updateEducationInformation(EducationInformationDto educationInformationDto, Short educationInfoId) {
        EducationInformation educationInformation=this.educationInformationRepository.findById(educationInfoId).orElseThrow(()->new ResourceNotFoundException("Education Information","Id",educationInfoId));
        educationInformation.setInstitutionName(educationInformationDto.getInstitutionName());
        educationInformation.setInstitutionAddress(educationInformationDto.getInstitutionAddress());
        educationInformation.setInstitutionContact(educationInformationDto.getInstitutionContact());
        educationInformation.setFromDate(educationInformationDto.getFromDate());
        educationInformation.setToDate(educationInformationDto.getToDate());
        educationInformation.setDegreeName(educationInformationDto.getDegreeName());
        educationInformation.setEducationDescription(educationInformation.getEducationDescription());
        educationInformation.setToPresent(educationInformationDto.isToPresent());
        EducationInformation updatedEducationInformation=this.educationInformationRepository.save(educationInformation);
        return this.modelMapper.map(updatedEducationInformation,EducationInformationDto.class);
    }

    @Override
    public void deleteEducationInformation(Short educationInfoId) {
        EducationInformation educationInformation=this.educationInformationRepository.findById(educationInfoId).orElseThrow(()->new ResourceNotFoundException("Education Information","Id",educationInfoId));
        this.educationInformationRepository.delete(educationInformation);

    }

    @Override
    public List<EducationInformationDto> getAllEducationInformation() {

        List<EducationInformation> educationInformations=this.educationInformationRepository.findAll();
        List<EducationInformationDto> educationInformationDtos=educationInformations.stream().map((educationInfo)->this.modelMapper.map(educationInfo,EducationInformationDto.class)).toList();
        return educationInformationDtos;
    }

    @Override
    public EducationInformationDto getEducationInformationById(Short educationInfoId) {
         EducationInformation educationInformation=this.educationInformationRepository.findById(educationInfoId).orElseThrow(()->new ResourceNotFoundException("Education Information","Id",educationInfoId));
         return this.modelMapper.map(educationInformation,EducationInformationDto.class);
    }
}
