package com.example.bank.model.entity;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "card_holder")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private  String name;

    @Column(name = "phone",unique = true,length = 15)
    private String phone;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id",foreignKey = @ForeignKey)
    private Address address;

    @OneToMany(mappedBy = "cardHolder",cascade = CascadeType.ALL)
    private Set<Account> accountSet = new HashSet<>();

}
