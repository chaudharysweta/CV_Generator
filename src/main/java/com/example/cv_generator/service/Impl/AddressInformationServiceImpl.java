package com.example.cv_generator.service.Impl;

import com.example.cv_generator.dto.AddressInformationDto;
import com.example.cv_generator.dto.BasicInformationDto;
import com.example.cv_generator.dto.IdNameDto;
import com.example.cv_generator.dto.LocalLevelDto;
import com.example.cv_generator.entity.*;
import com.example.cv_generator.exception.ResourceNotFoundException;
import com.example.cv_generator.repository.AddressInformationRepository;
import com.example.cv_generator.repository.BasicInformationRepository;
import com.example.cv_generator.repository.LocalLevelRepository;
import com.example.cv_generator.service.AddressInformationService;
import lombok.Builder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Builder
public class AddressInformationServiceImpl implements AddressInformationService {

    private final AddressInformationRepository addressInformationRepository;
    private final BasicInformationRepository basicInformationRepository;
    private final LocalLevelRepository localLevelRepository;
    private final ModelMapper modelMapper;

    public AddressInformationServiceImpl(AddressInformationRepository addressInformationRepository, BasicInformationRepository basicInformationRepository, LocalLevelRepository localLevelRepository, ModelMapper modelMapper) {
        this.addressInformationRepository = addressInformationRepository;
        this.basicInformationRepository = basicInformationRepository;
        this.localLevelRepository = localLevelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressInformationDto createAddress(AddressInformationDto addressInformationDto, Short basicId) {
        AddressInformation addressInformation=dtoToAddress(addressInformationDto,basicId);
        AddressInformation savedAddress=addressInformationRepository.save(addressInformation);
        return addressToDto(savedAddress);
    }

    @Override
    public AddressInformationDto updateAddress(AddressInformationDto addressInformationDto, Short addressId) {
        AddressInformation addressInformation=addressInformationRepository.findById(addressId)
                .orElseThrow(()->new ResourceNotFoundException("Address Information","Id",addressId));
        addressInformation.setAddressType(addressInformationDto.getAddressType());
        AddressInformation updatedAddress=addressInformationRepository.save(addressInformation);
        return modelMapper.map(updatedAddress,AddressInformationDto.class);
    }

    @Override
    public void deleteAddress(Short addressId) {
        AddressInformation addressInformation=addressInformationRepository.findById(addressId)
                .orElseThrow(()->new ResourceNotFoundException("Address Information","Id",addressId));
        addressInformationRepository.delete(addressInformation);

    }

    @Override
    public List<AddressInformationDto> getAllAddress() {
        List<AddressInformation> addressInformations=addressInformationRepository.findAll();
        List<AddressInformationDto> addressInformationDtos=addressInformations.stream().map((address)->modelMapper.map(address,AddressInformationDto.class)).toList();
        return addressInformationDtos;
    }

    @Override
    public AddressInformationDto getAddressById(Short addressId) {
        AddressInformation addressInformation=addressInformationRepository.findById(addressId)
                .orElseThrow(()->new ResourceNotFoundException("Address Information","Id",addressId));
        return modelMapper.map(addressInformation,AddressInformationDto.class);
    }

    @Override
    public List<AddressInformationDto> getAddressInfoByBasicInfoId(Short basicInfoId) {
        return toDto(addressInformationRepository.findAddressInformationByBasicInformationId(basicInfoId));
    }

    public List<AddressInformationDto> toDto(List<AddressInformation> addressInformationList){
        return addressInformationList.stream().map(this::addressToDto).collect(Collectors.toList());
    }

    //DTO Part
    public AddressInformation dtoToAddress(AddressInformationDto addressInformationDto, Short basicId){
        BasicInformation basicInformation=basicInformationRepository.findById(basicId)
                .orElseThrow(()->new ResourceNotFoundException("Basic Information","Id",basicId));

        AddressInformation addressInformation=new AddressInformation();
        addressInformation.setId(addressInformationDto.getId());
        addressInformation.setBasicInformation(new BasicInformation(basicId));
        addressInformation.setAddressType(addressInformationDto.getAddressType());
        addressInformation.setLocalLevel(new LocalLevel(addressInformationDto.getLocalLevelId()));
        addressInformation.setDistrict(new District(addressInformationDto.getDistrictId()));
        addressInformation.setProvince(new Province(addressInformationDto.getProvinceId()));
        addressInformation.setCountry(new Country(addressInformationDto.getCountryId()));
        return addressInformation;
    }
    public AddressInformationDto addressToDto(AddressInformation addressInformation){
        BasicInformationDto basicInformationDto=new BasicInformationDto();
        basicInformationDto.setId(addressInformation.getId());
        basicInformationDto.setFirstName(addressInformation.getBasicInformation().getFirstName());
        basicInformationDto.setMiddleName(addressInformation.getBasicInformation().getMiddleName());
        basicInformationDto.setLast_name(addressInformation.getBasicInformation().getLast_name());
        basicInformationDto.setBackground(addressInformation.getBasicInformation().getBackground());
        basicInformationDto.setTitle(addressInformation.getBasicInformation().getTitle());
        basicInformationDto.setMobileNumber(addressInformation.getBasicInformation().getMobileNumber());
        basicInformationDto.setEmail(addressInformation.getBasicInformation().getEmail());
        basicInformationDto.setLinkedInUrl(addressInformation.getBasicInformation().getLinkedInUrl());
        basicInformationDto.setProfileImage(addressInformation.getBasicInformation().getProfileImage());

        LocalLevelDto localLevelDto=new LocalLevelDto();
        localLevelDto.setId(addressInformation.getLocalLevel().getId());
        localLevelDto.setName(addressInformation.getLocalLevel().getName());
        localLevelDto.setNepaliName(addressInformation.getLocalLevel().getNepaliName());
        localLevelDto.setCode(addressInformation.getLocalLevel().getCode());
        localLevelDto.setTotalWardCount(addressInformation.getLocalLevel().getTotalWardCount());

        AddressInformationDto addressInformationDto=new AddressInformationDto();
        addressInformationDto.setId(addressInformation.getId());
        addressInformationDto.setAddressType(addressInformation.getAddressType());
        addressInformationDto.setLocal(getLocal(addressInformation.getLocalLevel()));
        addressInformationDto.setDistrict(getDistrict(addressInformation.getDistrict()));
        addressInformationDto.setProvince(getProvince(addressInformation.getProvince()));
        addressInformationDto.setCountry(getCountry(addressInformation.getCountry()));

        return addressInformationDto;
    }


    private IdNameDto getLocal(LocalLevel entity){
        if(entity == null)
            return null;

        return IdNameDto.builder().id(entity.getId()).name(entity.getName()).build();
    }

    private IdNameDto getDistrict(District entity){
        if(entity == null)
            return null;

        return IdNameDto.builder().id(entity.getId()).name(entity.getName()).build();
    }

    private IdNameDto getProvince(Province entity){
        if(entity == null)
            return null;

        return IdNameDto.builder().id(entity.getId()).name(entity.getName()).build();
    }

    private IdNameDto getCountry(Country entity){
        if(entity == null)
            return null;

        return IdNameDto.builder().id(entity.getId()).name(entity.getName()).build();
    }
}