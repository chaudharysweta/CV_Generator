package com.example.cv_generator.service;

import com.example.cv_generator.dto.AddressInformationDto;

import java.util.List;

public interface AddressInformationService {


    //create
    AddressInformationDto createAddress(AddressInformationDto addressInformationDto,Short basicId, Integer localId);

    //update
    AddressInformationDto updateAddress(AddressInformationDto addressInformationDto,Short addressId);

    //delete
    void deleteAddress(Short addressId);

    //get all
    List<AddressInformationDto> getAllAddress();

    //get by id
    AddressInformationDto getAddressById(Short addressId);
}
