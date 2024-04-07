package com.example.cv_generator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "experience_information",uniqueConstraints = {
        @UniqueConstraint(name = "unique_experience_information_company_contact",columnNames ="company_contact")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceInformation {

    @Id
    @SequenceGenerator(name = "experience_information_gen",sequenceName = "experience_information_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "experience_information_gen")
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "basic_id",foreignKey = @ForeignKey(name = "fk_experience_information_basic_information_id"))
    private BasicInformation basicInformation;

    @Column(name = "company_name",nullable = false,length = 200)
    private String companyName;

    @Column(name = "company_address",nullable = false,length = 200)
    private String companyAddress;

    @Column(name = "company_contact",nullable = false,length = 50)
    private String companyContact;

    @Column(name = "from_date",nullable = false)
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "to_present")
    private boolean toPresent;

    @OneToMany(mappedBy = "experienceInformation")
    private List<ProjectInformation> projectInformation;
}
