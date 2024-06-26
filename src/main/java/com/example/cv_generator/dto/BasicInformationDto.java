package com.example.cv_generator.dto;

import com.example.cv_generator.entity.EducationInformation;
import com.example.cv_generator.entity.ExperienceInformation;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasicInformationDto {

    private Short id;

    private String firstName;

    private String middleName;

    private String last_name;

    private String background;

    private String title;

    private String mobileNumber;

    private String email;

    private String linkedInUrl;

    private String profileImage;

}
