package com.example.cv_generator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fl_province")
public class Province {

    @Id
    @SequenceGenerator(name = "province_gen", sequenceName = "province_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "province_gen")
    private Short id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_n", unique = true)
    private String nepaliName;

    @Column(name = "code", unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "fk_province_country_id"))
    private Country country;

    public Province(Short id){
        this.id=id;
    }
}
