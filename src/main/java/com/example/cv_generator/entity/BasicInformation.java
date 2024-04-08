package com.example.cv_generator.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "basic_information",uniqueConstraints = {
        @UniqueConstraint(name = "uk_basicinfo_mobileno_email_linkedin",columnNames ={"mobile_number","email","linkedin_url"} )
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasicInformation {

    @Id
    @SequenceGenerator(name = "basic_info_gen",sequenceName = "basic_info_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "basic_info_gen")
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
    private List<ExperienceInformation> experienceInformation;

}
