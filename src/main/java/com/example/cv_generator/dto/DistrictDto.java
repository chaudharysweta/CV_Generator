package com.example.cv_generator.dto;

import com.example.cv_generator.entity.LocalLevel;
import com.example.cv_generator.entity.Province;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DistrictDto {

    private Integer id;

    private String name;

    private String nepaliName;

    private String code;

    private ProvinceDto province;


}
