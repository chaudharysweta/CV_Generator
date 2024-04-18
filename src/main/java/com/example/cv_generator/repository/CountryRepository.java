package com.example.cv_generator.repository;

import com.example.cv_generator.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Short> {
}
