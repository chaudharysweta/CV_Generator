package com.example.cv_generator.dto;

import com.example.cv_generator.entity.BasicInformation;
import com.example.cv_generator.entity.ProjectInformation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ExperienceInformationDto {

    private Short id;

    private String companyName;

    private String companyAddress;

    private String companyContact;

    private LocalDate fromDate;

    private LocalDate toDate;

    private boolean toPresent;

    private List<ProjectInformation> projectInformation;
}
