package com.example.cv_generator.repository;

import com.example.cv_generator.entity.EducationInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationInformationRepository extends JpaRepository<EducationInformation,Short> {
}
