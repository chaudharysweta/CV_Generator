package com.example.cv_generator.entity;

import com.example.cv_generator.enums.AddressType;
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
@Table(name = "address_information")
public class AddressInformation {

    @Id
    @SequenceGenerator(name = "address_information_gen",sequenceName = "address_information_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "address_information_gen")
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basic_id", foreignKey = @ForeignKey(name = "fk_address_information_basic_information_id"))
    private BasicInformation basicInformation;

    @Column(name = "address_type",length = 100)
    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localbody", foreignKey = @ForeignKey(name = "fk_address_information_locallevel_id"))
    private LocalLevel localLevel;
}
