package com.example.cv_generator.dto;

import com.example.cv_generator.entity.EducationInformation;
import com.example.cv_generator.entity.ExperienceInformation;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicInformationDto {

    private Short id;

    @NotEmpty(message = "First name is required")
    @Size(message = "Minimum and maximum letters of firstname is 2 and 100 respectively", min = 2, max = 100)
    private String firstName;

    private String middleName;

    @NotEmpty(message = "Last name is required")
    @Size(message = "Minimum and maximum letters of Last name is 2 and 100 respectively", min = 2, max = 100)
    private String last_name;

    @NotEmpty(message = "Background is required")
    private String background;

    @NotEmpty(message = "Title is required")
    @Size(message = "Minimum and maximum value for title is 2 and 100 respectively", min = 2, max = 100)
    private String title;

    @NotEmpty(message = "Mobile number is required")
    @Size(message = "Minimum and maximum numbers mobileNumber is 10 and 10 respectively", min = 10, max = 10)
    private String mobileNumber;

    @Email(message = "Invalid email address")
    @NotNull(message = "Email must not be null")
    @NotEmpty(message = "Email must not be empty")
    @Size(message = "Maximum value of email 100 ", max = 100)
    private String email;

    @NotNull(message = "LinkedIn URL must not be null")
    @Size(message = "Maximum value of url is 200 ", max = 200)
    private String linkedInUrl;

    private String profileImage;

}
