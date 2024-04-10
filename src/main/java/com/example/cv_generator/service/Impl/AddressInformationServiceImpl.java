package com.example.cv_generator.service.Impl;

import com.example.cv_generator.dto.AddressInformationDto;
import com.example.cv_generator.dto.BasicInformationDto;
import com.example.cv_generator.dto.LocalLevelDto;
import com.example.cv_generator.entity.AddressInformation;
import com.example.cv_generator.entity.BasicInformation;
import com.example.cv_generator.entity.LocalLevel;
import com.example.cv_generator.exception.ResourceNotFoundException;
import com.example.cv_generator.repository.AddressInformationRepository;
import com.example.cv_generator.repository.BasicInformationRepository;
import com.example.cv_generator.repository.LocalLevelRepository;
import com.example.cv_generator.service.AddressInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public AddressInformationDto createAddress(AddressInformationDto addressInformationDto, Short basicId, Integer localId) {
        AddressInformation addressInformation=dtoToAddress(addressInformationDto,basicId,localId);
        AddressInformation savedAddress=addressInformationRepository.save(addressInformation);
        return addressToDto(savedAddress,basicId,localId);
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

    //DTO Part
    public AddressInformation dtoToAddress(AddressInformationDto addressInformationDto, Short basicId, Integer localId){
        BasicInformation basicInformation=basicInformationRepository.findById(basicId)
                .orElseThrow(()->new ResourceNotFoundException("Basic Information","Id",basicId));
        LocalLevel localLevel=localLevelRepository.findById(localId)
                .orElseThrow(()->new ResourceNotFoundException("Local Level","Id",localId));
        AddressInformation addressInformation=new AddressInformation();
        addressInformation.setId(addressInformationDto.getId());
        addressInformation.setBasicInformation(basicInformation);
        addressInformation.setAddressType(addressInformationDto.getAddressType());
        addressInformation.setLocalLevel(localLevel);
        return addressInformation;
    }
    public AddressInformationDto addressToDto(AddressInformation addressInformation,Short basicId, Integer localId){
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
        addressInformationDto.setBasicInformation(basicInformationDto);
        addressInformationDto.setAddressType(addressInformation.getAddressType());
        addressInformationDto.setLocalLevel(localLevelDto);
        return addressInformationDto;
    }
}
