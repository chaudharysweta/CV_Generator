package com.example.cv_generator.service.Impl;

import com.example.cv_generator.dto.BasicInformationDto;
import com.example.cv_generator.dto.EducationInformationDto;
import com.example.cv_generator.entity.BasicInformation;
import com.example.cv_generator.entity.EducationInformation;
import com.example.cv_generator.exception.ResourceNotFoundException;
import com.example.cv_generator.repository.BasicInformationRepository;
import com.example.cv_generator.repository.EducationInformationRepository;
import com.example.cv_generator.service.EducationInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationInformationServiceImpl implements EducationInformationService {

    private final EducationInformationRepository educationInformationRepository;

    private final BasicInformationRepository basicInformationRepository;
    private final ModelMapper modelMapper;

    public EducationInformationServiceImpl(EducationInformationRepository educationInformationRepository, BasicInformationRepository basicInformationRepository, ModelMapper modelMapper) {
        this.educationInformationRepository = educationInformationRepository;
        this.basicInformationRepository = basicInformationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EducationInformationDto createEducationInformation(EducationInformationDto educationInformationDto, Short basicInfoId) {
        EducationInformation educationInformation = dtoToEduInfo(educationInformationDto, basicInfoId);
        EducationInformation saveEduInfo = educationInformationRepository.save(educationInformation);
        return eduInfoToDto(saveEduInfo);

    }


    @Override
    public EducationInformationDto updateEducationInformation(EducationInformationDto educationInformationDto, Short educationInfoId) {
        EducationInformation educationInformation = this.educationInformationRepository.findById(educationInfoId).orElseThrow(() -> new ResourceNotFoundException("Education Information", "Id", educationInfoId));
        educationInformation.setInstitutionName(educationInformationDto.getInstitutionName());
        educationInformation.setInstitutionAddress(educationInformationDto.getInstitutionAddress());
        educationInformation.setInstitutionContact(educationInformationDto.getInstitutionContact());
        educationInformation.setFromDate(educationInformationDto.getFromDate());
        educationInformation.setToDate(educationInformationDto.getToDate());
        educationInformation.setDegreeName(educationInformationDto.getDegreeName());
        educationInformation.setEducationDescription(educationInformation.getEducationDescription());
        educationInformation.setToPresent(educationInformationDto.getToPresent());
        EducationInformation updatedEducationInformation = this.educationInformationRepository.save(educationInformation);
        return this.modelMapper.map(updatedEducationInformation, EducationInformationDto.class);
    }

    @Override
    public void deleteEducationInformation(Short educationInfoId) {
        EducationInformation educationInformation = this.educationInformationRepository.findById(educationInfoId).orElseThrow(() -> new ResourceNotFoundException("Education Information", "Id", educationInfoId));
        this.educationInformationRepository.delete(educationInformation);

    }

    @Override
    public List<EducationInformationDto> getAllEducationInformation() {

        List<EducationInformation> educationInformation = this.educationInformationRepository.findAll();
        List<EducationInformationDto> educationInformationDtos = educationInformation.stream().map((educationInfo) -> this.modelMapper.map(educationInfo, EducationInformationDto.class)).toList();
        return educationInformationDtos;
    }

    @Override
    public EducationInformationDto getEducationInformationById(Short educationInfoId) {
        EducationInformation educationInformation = this.educationInformationRepository.findById(educationInfoId).orElseThrow(() -> new ResourceNotFoundException("Education Information", "Id", educationInfoId));
        return this.modelMapper.map(educationInformation, EducationInformationDto.class);
    }



    @Override
    public List<EducationInformationDto> getEducationByBasicId(Short basicInfoId) {
        return toDto(educationInformationRepository
                .findEducationInformationByBasicInformation(new BasicInformation(basicInfoId)));
    }

    public List<EducationInformationDto> toDto(List<EducationInformation> educationInformationList) {
        return educationInformationList.stream().map(this::eduInfoToDto).collect(Collectors.toList());
    }

    public EducationInformation dtoToEduInfo(EducationInformationDto educationInformationDto, Short basicInfoId) {
        BasicInformation basicInformation = basicInformationRepository.findById(basicInfoId)
                .orElseThrow(() -> new ResourceNotFoundException("Basic Information", "Id", basicInfoId));
        EducationInformation educationInformation = new EducationInformation();
        educationInformation.setInstitutionName(educationInformationDto.getInstitutionName());
        educationInformation.setInstitutionAddress(educationInformationDto.getInstitutionAddress());
        educationInformation.setInstitutionContact(educationInformationDto.getInstitutionContact());
        educationInformation.setFromDate(educationInformationDto.getFromDate());
        educationInformation.setToDate(educationInformationDto.getToDate());
        educationInformation.setToPresent(educationInformationDto.getToPresent());
        educationInformation.setDegreeName(educationInformationDto.getDegreeName());
        educationInformation.setEducationDescription(educationInformationDto.getEducationDescription());
        educationInformation.setBasicInformation(basicInformation);
        return educationInformation;
    }

    public EducationInformationDto eduInfoToDto(EducationInformation educationInformation) {
        BasicInformationDto basicInformationDto = new BasicInformationDto();
        basicInformationDto.setId(educationInformation.getBasicInformation().getId());
        EducationInformationDto educationInformationDto = new EducationInformationDto();
        educationInformationDto.setInstitutionName(educationInformation.getInstitutionName());
        educationInformationDto.setInstitutionAddress(educationInformation.getInstitutionAddress());
        educationInformationDto.setInstitutionContact(educationInformation.getInstitutionContact());
        educationInformationDto.setFromDate(educationInformation.getFromDate());
        educationInformationDto.setToDate(educationInformation.getToDate());
        educationInformationDto.setToPresent(educationInformation.getToPresent());
        educationInformationDto.setDegreeName(educationInformation.getDegreeName());
        educationInformationDto.setEducationDescription(educationInformation.getEducationDescription());
        educationInformationDto.setBasicInformation(basicInformationDto);
        return educationInformationDto;
    }

}
