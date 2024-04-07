package com.example.cv_generator.dto;

import com.example.cv_generator.entity.ExperienceInformation;
import com.example.cv_generator.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectInformationDto {

    private Short id;

    private ExperienceInformation experienceInformation;

    private String projectName;

    private ProjectStatus projectStatus;

    private String projectRole;

    private String projectDescription;

    private String techStack;

    private String projectUrl;
}
