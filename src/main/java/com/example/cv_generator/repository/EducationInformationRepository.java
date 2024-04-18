package com.example.cv_generator.repository;

import com.example.cv_generator.entity.BasicInformation;
import com.example.cv_generator.entity.EducationInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationInformationRepository extends JpaRepository<EducationInformation,Short> {

    List<EducationInformation> findEducationInformationByBasicInformation(BasicInformation basicInformation);
}
