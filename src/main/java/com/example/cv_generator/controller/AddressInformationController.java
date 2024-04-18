package com.example.cv_generator.controller;

import com.example.cv_generator.config.MessageCodeConstant;
import com.example.cv_generator.config.MessageConstant;
import com.example.cv_generator.dto.AddressInformationDto;
import com.example.cv_generator.dto.ApiResponse;
import com.example.cv_generator.dto.CustomMessageSource;
import com.example.cv_generator.dto.GlobalApiResponse;
import com.example.cv_generator.service.AddressInformationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressInformationController extends BaseController{

    private final AddressInformationService addressInformationService;
    private final CustomMessageSource customMessageSource;

    public AddressInformationController(AddressInformationService addressInformationService, CustomMessageSource customMessageSource) {
        this.addressInformationService = addressInformationService;
        this.customMessageSource = customMessageSource;
        this.messageCode= MessageCodeConstant.ADDRESS_INFORMATION;
    }

    @PostMapping("/create/{basic-id}")
    public ResponseEntity<GlobalApiResponse> createAddress(@Valid @RequestBody AddressInformationDto addressInformationDto,
                                                           @PathVariable("basic-id") Short basicId){
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_CREATE,customMessageSource.get(messageCode)),
                addressInformationService.createAddress(addressInformationDto,basicId)),HttpStatus.OK);
    }

    @PutMapping("/update/{address-id}")
    public ResponseEntity<GlobalApiResponse> updateAddress(@Valid@RequestBody AddressInformationDto addressInformationDto,
                                                               @PathVariable("address-id") Short addressId){
        addressInformationService.updateAddress(addressInformationDto,addressId);
        return ResponseEntity.ok(successResponse(customMessageSource.get(MessageConstant.CRUD_UPDATE,customMessageSource
                .get(MessageCodeConstant.ADDRESS_INFORMATION)),null));
    }


    @DeleteMapping("/delete/{address-id}")
    public ResponseEntity<GlobalApiResponse> deleteAddress(@PathVariable("address-id") Short addressId){
        if (addressId==null){
            throw new NullPointerException(MessageConstant.ID_NULL);
        }
        addressInformationService.deleteAddress(addressId);
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_DELETE,customMessageSource
                .get(MessageCodeConstant.ADDRESS_INFORMATION)),"Id:"+addressId),HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<GlobalApiResponse> getAllAddress(){
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_GET_ALL,customMessageSource
                .get(messageCode)),addressInformationService.getAllAddress()),HttpStatus.OK);
    }

    @GetMapping("/get/{address-id}")
    public ResponseEntity<GlobalApiResponse> getAddressById(@PathVariable("address-id") Short addressId){
        if (addressId==null){
            throw new NullPointerException(MessageConstant.ID_NULL);
        }
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_GET,customMessageSource
                .get(MessageCodeConstant.ADDRESS_INFORMATION)),addressInformationService.getAddressById(addressId)),HttpStatus.OK);
    }

    @GetMapping("/by-basic-info-id/{basic-info-id}")
    public ResponseEntity<GlobalApiResponse> getByBasicInfoId(@PathVariable("basic-info-id") Short basicInfoId){

        return new ResponseEntity<>(successResponse(customMessageSource
                .get(MessageConstant.CRUD_GET, customMessageSource
                        .get(messageCode)), addressInformationService.getAddressInfoByBasicInfoId(basicInfoId)), HttpStatus.OK);
    }
}