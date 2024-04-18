package com.example.cv_generator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fl_locallevel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalLevel {

    @Id
    @SequenceGenerator(name = "local_level_gen", sequenceName = "local_level_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "local_level_gen")
    private Short id;

    @Column(name = "name",unique = true,length = 100)
    private String name;

    @Column(name = "name_n",unique = true,length = 100)
    private String nepaliName;

    @Column(name = "code",unique = true,length = 10)
    private String code;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id",foreignKey = @ForeignKey(name = "fk_local_level_district_id"))
    private District district;

    @Column(name = "total_ward_count")
    private Integer totalWardCount;

    public LocalLevel(Short id){
        this.id=id;
    }
}
