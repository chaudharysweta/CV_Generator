package com.example.cv_generator.dto;

import com.example.cv_generator.entity.LocalLevel;
import com.example.cv_generator.entity.Province;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDto {

    private Short id;

    private String name;

    private String nepaliName;

    private String code;

    private ProvinceDto province;


}
