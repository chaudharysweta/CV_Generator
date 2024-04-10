package com.example.cv_generator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fl_district")
public class District {

    @Id
    @SequenceGenerator(name = "district_gen",sequenceName = "district_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "district_gen")
    private Integer id;

    @Column(name = "name",unique = true,length = 100)
    private String name;

    @Column(name = "name_n",unique = true,length = 100)
    private String nepaliName;

    @Column(name = "code",unique = true,length = 10)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "province_id", foreignKey = @ForeignKey(name = "fk_district_province_id"))
    private Province province;


}
