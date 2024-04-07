package com.example.cv_generator.entity;

import com.example.cv_generator.enums.DegreeName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "education_information",uniqueConstraints = {
        @UniqueConstraint(name = "unique_education_information_contact",columnNames = "institution_contact")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationInformation {

    @Id
    @SequenceGenerator(name = "education_information_gen",sequenceName = "education_information_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "education_information_gen")
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "basic_id",foreignKey = @ForeignKey(name = "fk_education_information_basic_information_id"))
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
