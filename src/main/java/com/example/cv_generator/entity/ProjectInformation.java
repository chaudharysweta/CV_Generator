package com.example.cv_generator.entity;

import com.example.cv_generator.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_information",uniqueConstraints = {
        @UniqueConstraint(name = "unique_project_information_name",columnNames ="project_name" ),
        @UniqueConstraint(name = "unique_project_information_tech_stack",columnNames = "tech_stack"),
        @UniqueConstraint(name = "unique_project_information_url",columnNames = "project_url")
})
public class ProjectInformation {

    @Id
    @SequenceGenerator(name = "project_information_gen",sequenceName = "project_information_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "project_information_gen")
    private Short id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "experience_id",foreignKey = @ForeignKey(name = "fk_project_information_experience_information_id"))
    private ExperienceInformation experienceInformation;

    @Column(name = "project_name",nullable = false,length = 100)
    private String projectName;

    @Column(name = "project_status",nullable = false,length = 30)
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    @Column(name = "project_role",nullable = false,length = 50)
    private String projectRole;

    @Column(name = "project_description",columnDefinition = "TEXT")
    private String projectDescription;

    @Column(name = "tech_stack",nullable = false,length = 200)
    private String techStack;

    @Column(name = "project_url",length = 200)
    private String projectUrl;
}
