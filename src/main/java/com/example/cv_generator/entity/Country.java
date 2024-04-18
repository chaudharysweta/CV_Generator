package com.example.cv_generator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "country", uniqueConstraints = {
        @UniqueConstraint(name = "unique_country_name", columnNames = "name")
})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    @Id
    @SequenceGenerator(name = "country_gen", sequenceName = "country_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_gen")
    private Short id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    public Country(Short id){
        this.id=id;
    }

}
