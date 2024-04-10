package com.example.cv_generator.service;

import com.example.cv_generator.dto.CountryDto;

import java.util.List;

public interface CountryService {

    //create
    CountryDto createCountry(CountryDto countryDto);

    //update
    CountryDto updateCountry(CountryDto countryDto, Integer countryId);

    //delete
    void deleteCountry(Integer countryId);

    //get all
    List<CountryDto> getAllCountry();

    //get by id
    CountryDto getCountryById(Integer countryId);

}
