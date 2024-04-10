package com.example.cv_generator.dto;

import com.example.cv_generator.entity.District;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LocalLevelDto {

    private Integer id;

    private String name;

    private String nepaliName;

    private String code;

    private DistrictDto district;

    private Integer totalWardCount;
}
