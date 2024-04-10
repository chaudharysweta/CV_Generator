package com.example.cv_generator.service.Impl;

import com.example.cv_generator.dto.CountryDto;
import com.example.cv_generator.entity.Country;
import com.example.cv_generator.exception.ResourceNotFoundException;
import com.example.cv_generator.repository.CountryRepository;
import com.example.cv_generator.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final ModelMapper modelMapper;
    private final CountryRepository countryRepository;

    public CountryServiceImpl(ModelMapper modelMapper, CountryRepository countryRepository) {
        this.modelMapper = modelMapper;
        this.countryRepository = countryRepository;
    }

    @Override
    public CountryDto createCountry(CountryDto countryDto) {
        Country country=modelMapper.map(countryDto,Country.class);
        Country savedCountry=countryRepository.save(country);
        return modelMapper.map(savedCountry,CountryDto.class);
    }

    @Override
    public CountryDto updateCountry(CountryDto countryDto, Integer countryId) {
        Country country=countryRepository.findById(countryId)
                .orElseThrow(()->new ResourceNotFoundException("Country","Id",countryId));
        country.setName(countryDto.getName());
        Country newCountry=countryRepository.save(country);
        return modelMapper.map(newCountry,CountryDto.class);
    }

    @Override
    public void deleteCountry(Integer countryId) {
        Country country=countryRepository.findById(countryId)
                .orElseThrow(()->new ResourceNotFoundException("Country","Id",countryId));
        countryRepository.delete(country);
    }

    @Override
    public List<CountryDto> getAllCountry() {
        List<Country> countries=countryRepository.findAll();
        List<CountryDto> countryDtos=countries.stream().map((country)->modelMapper.map(country,CountryDto.class)).toList();
        return countryDtos;
    }

    @Override
    public CountryDto getCountryById(Integer countryId) {
        Country country=countryRepository.findById(countryId)
                .orElseThrow(()->new ResourceNotFoundException("Country","Id",countryId));
        return modelMapper.map(country,CountryDto.class);
    }



}
