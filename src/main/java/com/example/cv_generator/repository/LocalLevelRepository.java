package com.example.cv_generator.repository;

import com.example.cv_generator.entity.LocalLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LocalLevelRepository extends JpaRepository<LocalLevel,Short> {

//    List<LocalLevel> findByAddressInformationBasicInformationId(Short basicInfoId);
//    List<LocalLevel> findLocalInformationByAddressInformationId(Short addressInfoId);


}
