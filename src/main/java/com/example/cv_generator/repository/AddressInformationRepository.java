package com.example.cv_generator.repository;

import com.example.cv_generator.entity.AddressInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressInformationRepository extends JpaRepository<AddressInformation,Short> {
}
