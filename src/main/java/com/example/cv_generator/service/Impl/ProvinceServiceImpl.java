package com.example.cv_generator.service.Impl;

import com.example.cv_generator.dto.CountryDto;
import com.example.cv_generator.dto.ProvinceDto;
import com.example.cv_generator.entity.Country;
import com.example.cv_generator.entity.Province;
import com.example.cv_generator.exception.ResourceNotFoundException;
import com.example.cv_generator.repository.CountryRepository;
import com.example.cv_generator.repository.ProvinceRepository;
import com.example.cv_generator.service.ProvinceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;

    public ProvinceServiceImpl(ProvinceRepository provinceRepository, CountryRepository countryRepository, ModelMapper modelMapper) {
        this.provinceRepository = provinceRepository;
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProvinceDto createdProvince(ProvinceDto provinceDto, Short countryId) {
        Province province=dtoToProvince(provinceDto,countryId);
        Province savedProvince=provinceRepository.save(province);
        return provinceToDto(savedProvince,countryId);
    }

    @Override
    public ProvinceDto updateProvince(ProvinceDto provinceDto, Short provinceId) {
        Province province=provinceRepository.findById(provinceId)
                .orElseThrow(()->new ResourceNotFoundException("Province","Id",provinceId));
        province.setName(provinceDto.getName());
        province.setNepaliName(provinceDto.getNepaliName());
        province.setCode(provinceDto.getCode());
        Province updatedProvince=provinceRepository.save(province);
        return modelMapper.map(updatedProvince,ProvinceDto.class);
    }

    @Override
    public void deleteProvince(Short provinceId) {
        Province province=provinceRepository.findById(provinceId)
                .orElseThrow(()->new ResourceNotFoundException("Province","Id",provinceId));
        provinceRepository.delete(province);

    }
    @Override
    public List<ProvinceDto> getAllProvince() {
        List<Province> provinces=provinceRepository.findAll();
        List<ProvinceDto> provinceDtos=provinces.stream().map((pro)->modelMapper.map(pro,ProvinceDto.class)).toList();
        return provinceDtos;
    }

    @Override
    public ProvinceDto getProvinceById(Short provinceId) {
        Province province=provinceRepository.findById(provinceId)
                .orElseThrow(()->new ResourceNotFoundException("Province","Id",provinceId));
        return modelMapper.map(province,ProvinceDto.class);
    }

    public Province dtoToProvince(ProvinceDto provinceDto,Short countryId){
        Country country = countryRepository.findById(countryId)
                .orElseThrow(()->new ResourceNotFoundException("Country","Id",countryId));
        Province province = new Province();
        province.setName(provinceDto.getName());
        province.setNepaliName(provinceDto.getNepaliName());
        province.setCode(provinceDto.getCode());
        province.setCountry(country);
        return province;
    }

    public ProvinceDto provinceToDto(Province province,Short countryId) {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(province.getCountry().getId());
        countryDto.setName(province.getCountry().getName());

        ProvinceDto provinceDto = new ProvinceDto();
        provinceDto.setId(province.getId());
        provinceDto.setName(province.getName());
        provinceDto.setNepaliName(province.getNepaliName());
        provinceDto.setCode(province.getCode());
        provinceDto.setCountry(countryDto);
        return provinceDto;

    }
}
