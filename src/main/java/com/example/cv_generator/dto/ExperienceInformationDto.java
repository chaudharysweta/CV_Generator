package com.example.cv_generator.dto;

import com.example.cv_generator.entity.BasicInformation;
import com.example.cv_generator.entity.ProjectInformation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceInformationDto {

    private Short id;

    private BasicInformationDto basicInformation;

    private String companyName;

    private String companyAddress;

    private String companyContact;

    private LocalDate fromDate;

    private LocalDate toDate;

    private boolean toPresent;

}
