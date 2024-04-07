package com.example.cv_generator.dto;

import com.example.cv_generator.entity.Country;
import com.example.cv_generator.entity.District;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProvinceDto {

    private Short id;

    private String name;

    private String nepaliName;

    private String code;

    private Country country;

    private List<District> district;
}
