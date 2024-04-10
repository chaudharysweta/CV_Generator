package com.example.cv_generator.service.Impl;

import com.example.cv_generator.dto.DistrictDto;
import com.example.cv_generator.dto.LocalLevelDto;
import com.example.cv_generator.entity.District;
import com.example.cv_generator.entity.LocalLevel;
import com.example.cv_generator.exception.ResourceNotFoundException;
import com.example.cv_generator.repository.DistrictRepository;
import com.example.cv_generator.repository.LocalLevelRepository;
import com.example.cv_generator.service.LocalLevelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalLevelServiceImpl implements LocalLevelService {

    private final DistrictRepository districtRepository;
    private final LocalLevelRepository localLevelRepository;
    private final ModelMapper modelMapper;

    public LocalLevelServiceImpl(DistrictRepository districtRepository, LocalLevelRepository localLevelRepository, ModelMapper modelMapper) {
        this.districtRepository = districtRepository;
        this.localLevelRepository = localLevelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LocalLevelDto createLocalLevel(LocalLevelDto localLevelDto, Integer districtId) {
        LocalLevel localLevel=dtoToLocal(localLevelDto,districtId);
        LocalLevel savedLocal=localLevelRepository.save(localLevel);
        return localToDto(savedLocal,districtId);
    }

    @Override
    public LocalLevelDto updateLocalLevel(LocalLevelDto localLevelDto, Integer localId) {
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
    public void deleteLocalLevel(Integer localId) {
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
    public LocalLevelDto getLocalDistrictById(Integer localId) {
        LocalLevel localLevel=localLevelRepository.findById(localId)
                .orElseThrow(()->new ResourceNotFoundException("Local Level","Id",localId));
        return modelMapper.map(localLevel,LocalLevelDto.class);
    }

    public LocalLevel dtoToLocal(LocalLevelDto localLevelDto,Integer districtId){
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
    public LocalLevelDto localToDto(LocalLevel localLevel,Integer districtId){
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
        localLevelDto.setDistrict(districtDto);
        localLevelDto.setTotalWardCount(localLevel.getTotalWardCount());
        return localLevelDto;
    }

}
