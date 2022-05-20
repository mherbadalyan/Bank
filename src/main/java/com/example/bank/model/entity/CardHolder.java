package com.example.bank.model.entity;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "card_holder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private  String name;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "address_id",foreignKey = @ForeignKey)
    private Address address;

    @OneToMany(mappedBy = "cardHolder")
    private Set<Account> accountSet = new HashSet<>();

}
