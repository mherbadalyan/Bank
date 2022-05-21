package com.example.bank.model.entity;


import com.example.bank.model.enums.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "address",
        uniqueConstraints = @UniqueConstraint(columnNames={"country", "city"}))
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country")
    private Country country;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "address")
    private Set<CardHolder> set = new HashSet<>();
}
