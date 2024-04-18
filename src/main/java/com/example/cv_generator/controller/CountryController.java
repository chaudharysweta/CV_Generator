package com.example.cv_generator.controller;

import com.example.cv_generator.config.MessageCodeConstant;
import com.example.cv_generator.config.MessageConstant;
import com.example.cv_generator.dto.ApiResponse;
import com.example.cv_generator.dto.CountryDto;
import com.example.cv_generator.dto.CustomMessageSource;
import com.example.cv_generator.dto.GlobalApiResponse;
import com.example.cv_generator.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController extends BaseController{

    private final CountryService countryService;

    private final CustomMessageSource customMessageSource;

    public CountryController(CountryService countryService, CustomMessageSource customMessageSource) {
        this.countryService = countryService;
        this.customMessageSource = customMessageSource;
        this.messageCode= MessageCodeConstant.COUNTRY;
    }

    //create
    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> createCountry(@RequestBody CountryDto countryDto){
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_CREATE,customMessageSource.
                get(messageCode)),countryService.createCountry(countryDto)),HttpStatus.OK);
    }


    //update
    @PutMapping("/update/{country-id}")
    public ResponseEntity<CountryDto> updateCountry(@RequestBody CountryDto countryDto,@PathVariable("country-id") Short countryId){
        CountryDto countryDto1=countryService.updateCountry(countryDto,countryId);
        return new ResponseEntity<>(countryDto1,HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/delete/{country-id}")
    public ResponseEntity<GlobalApiResponse> deleteCountry(@PathVariable("country-id") Short countryId){
        if (countryId==null){
            throw new NullPointerException(MessageConstant.ID_NULL);
        }
        countryService.deleteCountry(countryId);
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_DELETE,customMessageSource
                        .get(MessageCodeConstant.COUNTRY)),"Id:"+countryId),HttpStatus.OK);
    }

    //get all
    @GetMapping("/get-all")
    public ResponseEntity<GlobalApiResponse> getAllCountry(){
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_GET_ALL,customMessageSource
                        .get(messageCode)), countryService.getAllCountry()),HttpStatus.OK);
    }

    //get by id
    @GetMapping("/get/{country-id}")
    public ResponseEntity<GlobalApiResponse> getCountryById(@PathVariable("country-id") Short countryId){
        if (countryId==null){
            throw new NullPointerException(MessageConstant.ID_NULL);
        }
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_GET,customMessageSource.
                get(MessageCodeConstant.COUNTRY)),countryService.getCountryById(countryId)),HttpStatus.OK);
    }

}
