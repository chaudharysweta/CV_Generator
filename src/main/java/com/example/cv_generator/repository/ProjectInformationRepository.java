package com.example.cv_generator.repository;

import com.example.cv_generator.entity.ProjectInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectInformationRepository extends JpaRepository<ProjectInformation,Short> {
    List<ProjectInformation> findByExperienceInformation_BasicInformation_Id(Short basicInfoId);
    List<ProjectInformation> findProjectInformationByExperienceInformationId(Short experienceInfoId);
}
