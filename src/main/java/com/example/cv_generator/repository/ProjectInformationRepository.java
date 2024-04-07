package com.example.cv_generator.repository;

import com.example.cv_generator.entity.ProjectInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectInformationRepository extends JpaRepository<ProjectInformation,Short> {
}
