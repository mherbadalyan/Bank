package com.example.bank.model.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "account")
public class Account {

    @Id
    private Long accountNumber;

    @Column(name = "IBAN", unique = true)
    private String IBAN;

    @Column(name = "balance")
    private Long balance;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    @ManyToOne
    @JoinColumn(name = "CH_id")
    CardHolder cardHolder;

    @OneToMany(mappedBy = "account")
    Set<Card> card = new HashSet<>();
}
