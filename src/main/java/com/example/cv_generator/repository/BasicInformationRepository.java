package com.example.cv_generator.repository;

import com.example.cv_generator.entity.BasicInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicInformationRepository extends JpaRepository<BasicInformation,Short> {
}
