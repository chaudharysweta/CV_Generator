package com.example.cv_generator.repository;

import com.example.cv_generator.entity.BasicInformation;
import com.example.cv_generator.entity.EducationInformation;
import com.example.cv_generator.entity.ExperienceInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceInformationRepository extends JpaRepository<ExperienceInformation,Short> {
    List<ExperienceInformation> findExperienceInformationByBasicInformationId(short basicInformation);
}
