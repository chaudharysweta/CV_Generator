package com.example.cv_generator.service.Impl;

import com.example.cv_generator.dto.CountryDto;
import com.example.cv_generator.dto.DistrictDto;
import com.example.cv_generator.dto.ProvinceDto;
import com.example.cv_generator.entity.District;
import com.example.cv_generator.entity.Province;
import com.example.cv_generator.exception.ResourceNotFoundException;
import com.example.cv_generator.repository.DistrictRepository;
import com.example.cv_generator.repository.ProvinceRepository;
import com.example.cv_generator.service.DistrictService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final ProvinceRepository provinceRepository;
    private final ModelMapper modelMapper;

    public DistrictServiceImpl(DistrictRepository districtRepository, ProvinceRepository provinceRepository, ModelMapper modelMapper) {
        this.districtRepository = districtRepository;
        this.provinceRepository = provinceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DistrictDto createDistrict(DistrictDto districtDto, Integer provinceId) {
        District district=dtoToDistrict(districtDto,provinceId);
        District savedDistrict=districtRepository.save(district);
        return districtToDto(savedDistrict,provinceId);
    }

    @Override
    public DistrictDto updateDistrict(DistrictDto districtDto, Integer districtId) {
        District district=districtRepository.findById(districtId)
                .orElseThrow(()->new ResourceNotFoundException("District","Id",districtId));
        district.setName(districtDto.getName());
        district.setNepaliName(districtDto.getNepaliName());
        district.setCode(districtDto.getCode());
        District updatedDistrict=districtRepository.save(district);
        return modelMapper.map(updatedDistrict,DistrictDto.class);

    }

    @Override
    public void deleteDistrict(Integer districtId) {

        District district=districtRepository.findById(districtId)
                .orElseThrow(()->new ResourceNotFoundException("District","Id",districtId));
        districtRepository.delete(district);
    }

    @Override
    public List<DistrictDto> getAllDistrict() {
        List<District> districts=districtRepository.findAll();
        List<DistrictDto> districtDtos=districts.stream().map((district)->modelMapper.map(district,DistrictDto.class)).toList();
        return districtDtos;
    }

    @Override
    public DistrictDto getDistrictById(Integer districtId) {
        District district=districtRepository.findById(districtId)
                .orElseThrow(()->new ResourceNotFoundException("District","Id",districtId));
        return modelMapper.map(district,DistrictDto.class);
    }

    public District dtoToDistrict(DistrictDto districtDto,Integer provinceId){
        Province province=provinceRepository.findById(provinceId)
                .orElseThrow(()->new ResourceNotFoundException("Province","Id",provinceId));
        District district=new District();
        district.setId(districtDto.getId());
        district.setName(districtDto.getName());
        district.setNepaliName(districtDto.getNepaliName());
        district.setCode(districtDto.getCode());
        district.setProvince(province);
        return district;
    }
    public DistrictDto districtToDto(District district,Integer provinceId){


        ProvinceDto provinceDto=new ProvinceDto();
        provinceDto.setId(district.getId());
        provinceDto.setName(district.getName());
        provinceDto.setNepaliName(district.getNepaliName());
        provinceDto.setCode(district.getCode());

        DistrictDto districtDto=new DistrictDto();
        districtDto.setId(district.getId());
        districtDto.setName(district.getName());
        districtDto.setNepaliName(district.getNepaliName());
        districtDto.setCode(district.getCode());
        districtDto.setProvince(provinceDto);

        return districtDto;
    }

}
