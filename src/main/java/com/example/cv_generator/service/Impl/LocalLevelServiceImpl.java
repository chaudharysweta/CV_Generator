package com.example.cv_generator.service.Impl;

import com.example.cv_generator.dto.DistrictDto;
import com.example.cv_generator.dto.LocalLevelDto;
import com.example.cv_generator.dto.ProjectInformationDto;
import com.example.cv_generator.entity.District;
import com.example.cv_generator.entity.LocalLevel;
import com.example.cv_generator.exception.ResourceNotFoundException;
import com.example.cv_generator.repository.CountryRepository;
import com.example.cv_generator.repository.DistrictRepository;
import com.example.cv_generator.repository.LocalLevelRepository;
import com.example.cv_generator.repository.ProvinceRepository;
import com.example.cv_generator.service.LocalLevelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalLevelServiceImpl implements LocalLevelService {

    private final DistrictRepository districtRepository;
    private final LocalLevelRepository localLevelRepository;
    private final ModelMapper modelMapper;

    public LocalLevelServiceImpl(DistrictRepository districtRepository, LocalLevelRepository localLevelRepository, ProvinceRepository provinceRepository, CountryRepository countryRepository, ModelMapper modelMapper) {
        this.districtRepository = districtRepository;
        this.localLevelRepository = localLevelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LocalLevelDto createLocalLevel(LocalLevelDto localLevelDto, Short districtId) {
        LocalLevel localLevel=dtoToLocal(localLevelDto,districtId);
        LocalLevel savedLocal=localLevelRepository.save(localLevel);
        return localToDto(savedLocal);
    }

    @Override
    public LocalLevelDto updateLocalLevel(LocalLevelDto localLevelDto, Short localId) {
        LocalLevel localLevel=localLevelRepository.findById(localId)
                .orElseThrow(()->new ResourceNotFoundException("Local Level","Id",localId));
        localLevel.setName(localLevelDto.getName());
        localLevel.setNepaliName(localLevelDto.getNepaliName());
        localLevel.setCode(localLevelDto.getCode());
        localLevel.setTotalWardCount(localLevelDto.getTotalWardCount());
        LocalLevel updatedLocal=localLevelRepository.save(localLevel);
        return modelMapper.map(updatedLocal,LocalLevelDto.class);
    }

    @Override
    public void deleteLocalLevel(Short localId) {
        LocalLevel localLevel=localLevelRepository.findById(localId)
                .orElseThrow(()->new ResourceNotFoundException("Local Level","Id",localId));
        localLevelRepository.delete(localLevel);

    }
    @Override
    public List<LocalLevelDto> getAllLocalLevel() {
        List<LocalLevel> localLevels=localLevelRepository.findAll();
        List<LocalLevelDto> localLevelDtos=localLevels.stream().map((local)->modelMapper.map(local,LocalLevelDto.class)).toList();
        return localLevelDtos;
    }
    @Override
    public LocalLevelDto getLocalDistrictById(Short localId) {
        LocalLevel localLevel=localLevelRepository.findById(localId)
                .orElseThrow(()->new ResourceNotFoundException("Local Level","Id",localId));
        return modelMapper.map(localLevel,LocalLevelDto.class);
    }

//    @Override
//    public List<LocalLevelDto> getLocalInfoByAddressInfoId(Short experienceInfoId) {
//        return  toDto(localLevelRepository.findLocalInformationByAddressInformationId(experienceInfoId));
//    }
//
//    @Override
//    public List<LocalLevelDto> getLocalInfoByBasicInfoId(Short basicInfoId) {
//        return toDto(localLevelRepository.findByAddressInformationBasicInformationId(basicInfoId));
//    }
//
//
//    public List<LocalLevelDto> toDto(List<LocalLevel> localLevelList){
//        return localLevelList.stream().map(this::localToDto).collect(Collectors.toList());
//    }



    public LocalLevel dtoToLocal(LocalLevelDto localLevelDto,Short districtId){
        District district=districtRepository.findById(districtId)
                .orElseThrow(()->new ResourceNotFoundException("District","Id",districtId));
        LocalLevel localLevel=new LocalLevel();
        localLevel.setId(localLevelDto.getId());
        localLevel.setName(localLevelDto.getName());
        localLevel.setNepaliName(localLevelDto.getNepaliName());
        localLevel.setCode(localLevelDto.getCode());
        localLevel.setDistrict(district);
        localLevel.setTotalWardCount(localLevelDto.getTotalWardCount());
        return localLevel;
    }
    public LocalLevelDto localToDto(LocalLevel localLevel){
        DistrictDto districtDto=new DistrictDto();
        districtDto.setId(localLevel.getId());
        districtDto.setName(localLevel.getName());
        districtDto.setNepaliName(localLevel.getNepaliName());
        districtDto.setCode(localLevel.getCode());

        LocalLevelDto localLevelDto=new LocalLevelDto();
        localLevelDto.setId(localLevel.getId());
        localLevelDto.setName(localLevel.getName());
        localLevelDto.setNepaliName(localLevel.getNepaliName());
        localLevelDto.setCode(localLevel.getCode());
        //localLevelDto.setDistrict(districtDto);
        localLevelDto.setTotalWardCount(localLevel.getTotalWardCount());
        return localLevelDto;
    }

}
