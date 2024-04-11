package com.example.cv_generator.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Integer id;
    @NotEmpty
    @Size(min = 4,message = "Username must be min of 4 character")
    private String name;
    @Email(message = "Email address is not valid")
    private String email;
    @Pattern(regexp="^\\+(?:[0-9]●?){6,14}[0-9]$", message="Phone number is not valid")
    private String phoneNumber;
    @NotEmpty
    @Size(min = 3,max = 10,message = "Password must be min 3 chars and maximum 4 chars")
    private String password;

    private Set<RoleDto> roles = new HashSet<>();
}
