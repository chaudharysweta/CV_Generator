package com.example.cv_generator.dto;

import com.example.cv_generator.entity.Country;
import com.example.cv_generator.entity.District;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceDto {

    private Short id;

    private String name;

    private String nepaliName;

    private String code;

    private CountryDto country;


}
