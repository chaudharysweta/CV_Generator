package com.example.cv_generator.dto;

import com.example.cv_generator.entity.ExperienceInformation;
import com.example.cv_generator.enums.ProjectStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectInformationDto {

    private Short id;

    private Short experienceInformationId;

    @NotEmpty(message = "Project Name must not be empty")
    @Size(message = "Project name must not exceed 100", max = 100)
    private String projectName;

    @NotEmpty(message = "Project status must not be empty")
    private ProjectStatus projectStatus;

    @NotEmpty(message = "Project Role must not be empty")
    @Size(message = "Project Role must not exceed more than 50", max = 50)
    private String projectRole;

    @NotEmpty(message = "Project Description must not be empty")
    private String projectDescription;

    private String techStack;

    @Size(message = "URL must not exceed 200", max = 200)
    private String projectUrl;
}
