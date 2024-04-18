package com.example.cv_generator.dto;

import com.example.cv_generator.entity.BasicInformation;
import com.example.cv_generator.entity.District;
import com.example.cv_generator.entity.LocalLevel;
import com.example.cv_generator.entity.Province;
import com.example.cv_generator.enums.AddressType;
import com.example.cv_generator.pdf.IdNameDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressInformationDto {

    private Short id;


    private Short basicInformationId;

    @NotNull(message = "Address type is required")
    private AddressType addressType;

    @NotNull(message = "Locallevel ID is required")
    private Short localLevelId;

    @NotNull(message = "Country ID is required")
    private Short countryId;

    @NotNull(message = "District ID is required")
    private Short districtId;

    private Short provinceId;

    private IdNameDto province;

    private IdNameDto country;

    private IdNameDto district;

    private IdNameDto local;
}