package com.example.bank.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "account",cascade = CascadeType.REMOVE)
    List<Card> card = new ArrayList<>();
}
