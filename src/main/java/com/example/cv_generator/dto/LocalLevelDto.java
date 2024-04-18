package com.example.cv_generator.dto;

import com.example.cv_generator.entity.District;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LocalLevelDto {

    private Short id;

    private String name;

    private String nepaliName;

    private String code;

    private Short district;

    private Integer totalWardCount;
}
