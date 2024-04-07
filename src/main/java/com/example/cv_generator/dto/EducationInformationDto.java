package com.example.cv_generator.dto;

import com.example.cv_generator.entity.BasicInformation;
import com.example.cv_generator.enums.DegreeName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class EducationInformationDto {

    private Short id;

    private BasicInformation basicInformation;

    private String institutionName;

    private String institutionAddress;

    private String institutionContact;

    private LocalDate fromDate;

    private LocalDate toDate;

    private boolean toPresent;

    private DegreeName degreeName;

    private String educationDescription;
}
