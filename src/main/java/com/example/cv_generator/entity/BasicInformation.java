package com.example.cv_generator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "basic_information",uniqueConstraints = {
        @UniqueConstraint(name = "basic_information_mobile_number",columnNames = "mobile_number"),
        @UniqueConstraint(name = "basic_information_email",columnNames = "email"),
        @UniqueConstraint(name = "basic_information_linkedIn_url",columnNames = "linkedIn_url")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasicInformation {

    @Id
    @SequenceGenerator(name = "basic_information_gen",sequenceName = "basic_information_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "basic_information_gen")
    private Short id;

    @Column(name = "first_name",nullable = false,length = 50)
    private String firstName;

    @Column(name = "middle_name",length = 50)
    private String middleName;

    @Column(name = "last_name",nullable = false,length = 50)
    private String last_name;

    @Column(name = "background",nullable = false,columnDefinition = "TEXT")
    private String background;

    @Column(name = "title",nullable = false,length = 100)
    private String title;

    @Column(name = "mobile_number",nullable = false,length = 10)
    private String mobileNumber;

    @Column(name = "email",nullable = false,length = 100)
    private String email;

    @Column(name = "linkedIn_url",nullable = false,length = 200)
    private String linkedInUrl;

    @Column(name = "profile_image",nullable = false,length = 200)
    private String profileImage;

    @OneToMany(mappedBy = "basicInformation")
    private List<EducationInformation> educationInformation;

    @OneToMany(mappedBy = "basicInformation")
    private List<ExperienceInformation> experienceInformation;

}
