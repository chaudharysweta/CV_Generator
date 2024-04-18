package com.example.cv_generator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class PdfGeneratorDto {

    private List<BasicInformationDto> basicInformation;
    private List<EducationInformationDto> educationInformation;
    private List<ExperienceInformationDto> experienceInformation;
    private List<ProjectInformationDto> projectInformation;
    private List<AddressInformationDto> addressInformation;
    private List<CountryDto> country;
    private List<ProvinceDto> province;
    private List<DistrictDto> district;
    private List<LocalLevelDto> localLevel;
}
