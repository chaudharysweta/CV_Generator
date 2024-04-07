package com.example.cv_generator.dto;

import com.example.cv_generator.entity.BasicInformation;
import com.example.cv_generator.entity.District;
import com.example.cv_generator.entity.LocalLevel;
import com.example.cv_generator.entity.Province;
import com.example.cv_generator.enums.AddressType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressInformationDto {

    private Short id;

    private BasicInformation basicInformation;

    private AddressType addressType;

    private Province province;

    private District district;

    private LocalLevel localLevel;
}
