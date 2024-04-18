package com.example.cv_generator.repository;

import com.example.cv_generator.entity.AddressInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressInformationRepository extends JpaRepository<AddressInformation,Short> {
    List<AddressInformation> findAddressInformationByBasicInformationId(Short basicInfoId);

}
