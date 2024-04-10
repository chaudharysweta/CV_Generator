package com.example.cv_generator.controller;

import com.example.cv_generator.dto.AddressInformationDto;
import com.example.cv_generator.dto.ApiResponse;
import com.example.cv_generator.service.AddressInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressInformationController {

    private final AddressInformationService addressInformationService;

    public AddressInformationController(AddressInformationService addressInformationService) {
        this.addressInformationService = addressInformationService;
    }

    @PostMapping("/create/{basic-id}/{local-id}")
    public ResponseEntity<AddressInformationDto> createAddress(@RequestBody AddressInformationDto addressInformationDto, @PathVariable("basic-id") Short basicId,@PathVariable("local-id") Integer localId){
        AddressInformationDto addressInformationDto1=addressInformationService.createAddress(addressInformationDto,basicId,localId);
        return new ResponseEntity<>(addressInformationDto1, HttpStatus.CREATED);
    }

    @PutMapping("/update/{address-id}")
    public ResponseEntity<AddressInformationDto> updateAddress(@RequestBody AddressInformationDto addressInformationDto,@PathVariable("address-id") Short addressId){
        AddressInformationDto addressInformationDto1=addressInformationService.updateAddress(addressInformationDto,addressId);
        return new ResponseEntity<>(addressInformationDto1,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{address-id}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable("address-id") Short addressId){
        addressInformationService.deleteAddress(addressId);
        return new ResponseEntity<>(new ApiResponse("Address Information Deleted Successfully",true),HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<AddressInformationDto>> getAllAddress(){
        List<AddressInformationDto> addressInformationDtos=addressInformationService.getAllAddress();
        return ResponseEntity.ok(addressInformationDtos);
    }

    @GetMapping("/get/{address-id}")
    public ResponseEntity<AddressInformationDto> getAddressById(@PathVariable("address-id") Short addressId){
        AddressInformationDto addressInformationDto= addressInformationService.getAddressById(addressId);
        return new ResponseEntity<>(addressInformationDto,HttpStatus.OK);
    }
}
