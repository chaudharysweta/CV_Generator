package com.example.cv_generator.entity;

import com.example.cv_generator.enums.DegreeName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "education_information",uniqueConstraints = {
        @UniqueConstraint(name = "uk_eduinfo_contact",columnNames = "institution_contact")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationInformation {

    @Id
    @SequenceGenerator(name = "edu_info_gen",sequenceName = "edu_info_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "edu_info_gen")
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "basic_id",foreignKey = @ForeignKey(name = "fk_edu_info_basic_info_id"))
    private BasicInformation basicInformation;

    @Column(name = "institution_name", nullable = false,length = 200)
    private String institutionName;

    @Column(name = "institution_address",nullable = false,length = 200)
    private String institutionAddress;

    @Column(name = "institution_contact",nullable = false,length = 50)
    private String institutionContact;

    @Column(name = "from_date",nullable = false)
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "to_present")
    private boolean toPresent;

    @Column(name = "degree_name",nullable = false,length = 100)
    @Enumerated(EnumType.STRING)
    private DegreeName degreeName;

    @Column(name = "education_description",nullable = false,length = 100)
    private String educationDescription;
}
