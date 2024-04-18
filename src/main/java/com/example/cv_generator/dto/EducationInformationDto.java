package com.example.cv_generator.dto;

import com.example.cv_generator.entity.BasicInformation;
import com.example.cv_generator.enums.DegreeName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class EducationInformationDto {

    private Short id;


    private BasicInformationDto basicInformation;

    @NotBlank(message = "Institute name cannot be empty")
    @Size(message = "Values between 3 and 200" , max = 200,min = 3)
    private String institutionName;

    @Size(message = "Values upto 200", max = 200)
    private String institutionAddress;

    @NotBlank(message = "Institute contact cannot be empty")
    @Size(message = "Minimum and maximum numbers mobileNumber is 10 and 10 respectively", min = 10, max = 10)
    private String institutionContact;

    @NotNull(message = "From date must not be null")
    private LocalDate fromDate;

    private LocalDate toDate;

    private Boolean toPresent;

    @NotNull(message = "Degree cannot be empty")
    private DegreeName degreeName;

    @NotBlank(message = "Education description must not be empty")
    @Size(message = "Values must not exceed 100", max = 100)
    private String educationDescription;
}
