package com.example.cv_generator.controller;

import com.example.cv_generator.dto.ApiResponse;
import com.example.cv_generator.dto.CountryDto;
import com.example.cv_generator.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    //create
    @PostMapping("/create")
    public ResponseEntity<CountryDto> createCountry(@RequestBody CountryDto countryDto){
        CountryDto countryDto1=countryService.createCountry(countryDto);
        return new ResponseEntity<>(countryDto1, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/update/{country-id}")
    public ResponseEntity<CountryDto> updateCountry(@RequestBody CountryDto countryDto,@PathVariable("country-id") Integer countryId){
        CountryDto countryDto1=countryService.updateCountry(countryDto,countryId);
        return new ResponseEntity<>(countryDto1,HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/delete/{country-id}")
    public ResponseEntity<ApiResponse> deleteCountry(@PathVariable("country-id") Integer countryId){
        countryService.deleteCountry(countryId);
        return new ResponseEntity<>(new ApiResponse("Country is deleted successfully",true),HttpStatus.OK);
    }

    //get all
    @GetMapping("/get-all")
    public ResponseEntity<List<CountryDto>> getAllCountry(){
        List<CountryDto> countryDtos=countryService.getAllCountry();
        return ResponseEntity.ok(countryDtos);
    }

    //get by id
    @GetMapping("/get/{country-id}")
    public ResponseEntity<CountryDto> getCountryById(@PathVariable("country-id") Integer countryId){
        CountryDto countryDto= countryService.getCountryById(countryId);
        return new ResponseEntity<>(countryDto,HttpStatus.OK);
    }
}
